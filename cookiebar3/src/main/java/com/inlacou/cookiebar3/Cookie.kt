package com.inlacou.cookiebar3

import android.animation.Animator
import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.AttrRes
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.github.florent37.kotlin.pleaseanimate.PleaseAnim
import com.github.florent37.kotlin.pleaseanimate.please

internal class Cookie @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : LinearLayout(context, attrs, defStyleAttr) {
    
    private var layoutCookie: ViewGroup? = null
    private var tvTitle: TextView? = null
    private var tvMessage: TextView? = null
    private var ivIcon: ImageView? = null
    var layoutGravity = Gravity.BOTTOM
    private var animationEndListener: ((animationIndex: Int, tag: String?, hold: Boolean) -> Unit)? = null
    private var dismissListener: (() -> Unit)? = null
    private var steps: MutableList<CookieAnimationStep> = mutableListOf()
    
    private fun initViews(@LayoutRes rootView: Int, viewInitializer: CookieBar.CustomViewInitializer?) {
        if (rootView != 0) {
            View.inflate(context, rootView, this)
            viewInitializer?.initView(getChildAt(0))
        } else {
            View.inflate(context, R.layout.layout_cookie, this)
        }
        
        if (getChildAt(0).layoutParams is LayoutParams) (getChildAt(0).layoutParams as LayoutParams).gravity = Gravity.BOTTOM
        
        layoutCookie = findViewById(R.id.cookie)
        tvTitle = findViewById(R.id.tv_title)
        tvMessage = findViewById(R.id.tv_message)
        ivIcon = findViewById(R.id.iv_icon)
    }
    
    fun setParams(params: CookieBar.Params) {
        initViews(params.customViewResource, params.viewInitializer)
        
        layoutGravity = params.cookiePosition
        animationEndListener = params.animationEndListener
        dismissListener = params.dismissListener
        
        layoutCookie!!.visibility = View.INVISIBLE
        steps.addAll(params.steps)
        
        //Icon
        if (params.iconResId != 0) {
            ivIcon?.visibility = View.VISIBLE
            ivIcon?.setImageResource(params.iconResId)
            params.iconAnimator?.apply {
                setTarget(ivIcon)
                start()
            }
        }
        
        //Title
        if (!TextUtils.isEmpty(params.title)) {
            tvTitle?.visibility = View.VISIBLE
            tvTitle?.text = params.title
            if (params.titleColor != 0) {
                tvTitle?.setTextColor(ContextCompat.getColor(context, params.titleColor))
            }
            setDefaultTextSize(tvTitle, R.attr.cookieTitleSize)
        }
        
        //Message
        if (!TextUtils.isEmpty(params.message)) {
            tvMessage?.visibility = View.VISIBLE
            tvMessage?.text = params.message
            if (params.messageColor != 0) {
                tvMessage?.setTextColor(ContextCompat.getColor(context, params.messageColor))
            }
            setDefaultTextSize(tvMessage, R.attr.cookieMessageSize)
        }
        
        val defaultPadding = context.resources.getDimensionPixelSize(R.dimen.default_padding)
        val padding = ThemeResolver.getDimen(context, R.attr.cookiePadding, defaultPadding)
        
        if (layoutGravity == Gravity.BOTTOM) {
            layoutCookie?.setPadding(padding, padding, padding, padding)
        }
        
        var auxSteps = listOf<PleaseAnim>()
        auxSteps = steps.flatMap {
            if(it.holdOnPosition>0) listOf(it, it.copy(duration = it.holdOnPosition, holdOnPosition = -1337))
            else listOf(it)
        }.mapIndexed { index, step ->
            please(step.duration, interpolator = step.interpolator ?: LinearInterpolator()) {
                step.animations.forEach { animate(findViewById(it.target ?: R.id.cookie)).toBe(it.expectations) }
            }.withEndAction {
                animationEndListener?.invoke(index, step.tag, step.holdOnPosition==-1337L)
                if(index+1<auxSteps.size) auxSteps[index+1].start()
                if(index+1==auxSteps.size) {
                    visibility = View.GONE
                    removeFromParent()
                }
            }
        }
        auxSteps.firstOrNull()?.start()
    }
    
    private fun setDefaultTextSize(textView: TextView?, @AttrRes attr: Int) {
        val size = ThemeResolver.getDimen(context, attr, 0).toFloat()
        if (size > 0) {
            textView?.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
        }
    }
    
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (layoutGravity == Gravity.TOP) {
            super.onLayout(changed, l, 0, r, layoutCookie?.measuredHeight ?: 0)
        } else {
            super.onLayout(changed, l, t, r, b)
        }
    }
    
    @JvmOverloads
    fun dismiss(listener: (() -> Unit)? = null) {
        println("Cookie | dismiss: $listener")
    
        listener?.invoke()
        visibility = View.GONE
        removeFromParent()
        /* TODO stop gracefully, making the out animation
        steps.lastOrNull()?.let { step ->
            println("Cookie | dismiss: lastOrNull")
            please(step.duration) {
                step.animations.forEach { animate(findViewById(it.target ?: R.id.cookie)).toBe(it.expectations) }
            }.withEndAction {
                println("Cookie | dismiss: withEndAction")
            }.now()
        }*/
    }
    
    private fun removeFromParent() {
        postDelayed({
            val parent = parent
            if (parent != null) {
                this@Cookie.clearAnimation()
                (parent as ViewGroup).removeView(this@Cookie)
                dismissListener?.invoke()
            }
        }, 200)
    }
    
}
