package com.inlacou.cookiebar3

import android.animation.Animator
import android.content.Context
import android.graphics.Color
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
import android.view.ViewParent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

internal class Cookie @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                                defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr), View.OnTouchListener {
    private var slideOutAnimation: Animation? = null
    private var layoutCookie: ViewGroup? = null
    private var tvTitle: TextView? = null
    private var tvMessage: TextView? = null
    private var ivIcon: ImageView? = null
    private var duration: Long = 2000
    var layoutGravity = Gravity.BOTTOM
        private set
    private var initialDragX: Float = 0.toFloat()
    private var dismissOffsetThreshold: Float = 0.toFloat()
    private var viewWidth: Float = 0.toFloat()
    private var swipedOut: Boolean = false
    private var animationInTop: Int = 0
    private var animationInBottom: Int = 0
    private var animationOutTop: Int = 0
    private var animationOutBottom: Int = 0
    private var isAutoDismissEnabled: Boolean = false
    private var isSwipeable: Boolean = false
    private var shownListener: (() -> Unit)? = null
    private var dismissListener: (() -> Unit)? = null

    private val destroyListener: Animator.AnimatorListener
        get() = object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}

            override fun onAnimationEnd(animation: Animator) {
                removeFromParent()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}
        }

    private fun initViews(@LayoutRes rootView: Int, viewInitializer: CookieBar.CustomViewInitializer?) {
        if (rootView != 0) {
            View.inflate(context, rootView, this)
            viewInitializer?.initView(getChildAt(0))
        } else {
            View.inflate(context, R.layout.layout_cookie, this)
        }

        if (getChildAt(0).layoutParams is LayoutParams) {
            val lp = getChildAt(0).layoutParams as LayoutParams
            lp.gravity = Gravity.BOTTOM
        }

        layoutCookie = findViewById(R.id.cookie)
        tvTitle = findViewById(R.id.tv_title)
        tvMessage = findViewById(R.id.tv_message)
        ivIcon = findViewById(R.id.iv_icon)

        //validateLayoutIntegrity()
        //initDefaultStyle(context)
        layoutCookie?.setOnTouchListener(this)
    }

    /**
     * Init the default text color or background color. You can change the default style by set the
     * Theme's attributes.
     *
     *
     * <pre>
     * <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <item name="cookieTitleColor">@color/default_title_color</item>
    <item name="cookieMessageColor">@color/default_message_color</item>
    <item name="cookieActionColor">@color/default_action_color</item>
    <item name="cookieBackgroundColor">@color/default_bg_color</item>
    </style>
    </pre> *
     */
    private fun initDefaultStyle(context: Context) {
        val titleColor = ThemeResolver.getColor(context, R.attr.cookieTitleColor, Color.WHITE)
        val messageColor = ThemeResolver.getColor(context, R.attr.cookieMessageColor, Color.WHITE)
        val backgroundColor = ThemeResolver.getColor(context, R.attr.cookieBackgroundColor,
                ContextCompat.getColor(context, R.color.default_bg_color))

        tvTitle?.setTextColor(titleColor)
        tvMessage?.setTextColor(messageColor)
        layoutCookie?.setBackgroundColor(backgroundColor)
    }

    fun setParams(params: CookieBar.Params) {
        initViews(params.customViewResource, params.viewInitializer)

        duration = params.duration
        layoutGravity = params.cookiePosition
        animationInTop = params.animationInTop
        animationInBottom = params.animationInBottom
        animationOutTop = params.animationOutTop
        animationOutBottom = params.animationOutBottom
        isSwipeable = params.enableSwipeToDismiss
        isAutoDismissEnabled = params.enableAutoDismiss
        shownListener = params.shownListener
        dismissListener = params.dismissListener

        //Icon
        if (params.iconResId != 0) {
            ivIcon?.visibility = View.VISIBLE
            ivIcon?.setBackgroundResource(params.iconResId)
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

        createInAnim()
        createOutAnim()
    }

    private fun setDefaultTextSize(textView: TextView?, @AttrRes attr: Int) {
        val size = ThemeResolver.getDimen(context, attr, 0).toFloat()
        if (size > 0) {
            textView?.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        viewWidth = width.toFloat()
        dismissOffsetThreshold = viewWidth / 3

        if (layoutGravity == Gravity.TOP) {
            super.onLayout(changed, l, 0, r, layoutCookie?.measuredHeight ?: 0)
        } else {
            super.onLayout(changed, l, t, r, b)
        }
    }

    private fun createInAnim() {
        val animationResId = if (layoutGravity == Gravity.BOTTOM) animationInBottom else animationInTop
        val slideInAnimation = AnimationUtils.loadAnimation(context, animationResId)
        slideInAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                // no implementation
            }

            override fun onAnimationEnd(animation: Animation) {
                shownListener?.invoke()
                if (!isAutoDismissEnabled) {
                    return
                }

                postDelayed({ dismiss() }, duration)
            }

            override fun onAnimationRepeat(animation: Animation) {
                // no implementation
            }
        })

        animation = slideInAnimation
    }

    private fun createOutAnim() {
        val animationResId = if (layoutGravity == Gravity.BOTTOM) animationOutBottom else animationOutTop
        slideOutAnimation = AnimationUtils.loadAnimation(context, animationResId)
    }

    @JvmOverloads
    fun dismiss(listener: (() -> Unit)? = null) {
        if (swipedOut) {
            removeFromParent()
            return
        }

        slideOutAnimation?.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {
                // no implementation
            }

            override fun onAnimationEnd(animation: Animation) {
                listener?.invoke()
                visibility = View.GONE
                removeFromParent()
            }

            override fun onAnimationRepeat(animation: Animation) {
                // no implementation
            }
        })

        startAnimation(slideOutAnimation)
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

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (!isSwipeable) {
            return true
        }

        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                initialDragX = motionEvent.rawX
                return true
            }

            MotionEvent.ACTION_UP -> {
                if (!swipedOut) {
                    view.animate()
                            .x(0f)
                            .alpha(1f)
                            .setDuration(200)
                            .start()
                }
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                if (swipedOut) {
                    return true
                }
                var offset = motionEvent.rawX - initialDragX
                var alpha = 1 - Math.abs(offset / viewWidth)
                var duration: Long = 0

                if (Math.abs(offset) > dismissOffsetThreshold) {
                    offset = viewWidth * Math.signum(offset)
                    alpha = 0f
                    duration = 200
                    swipedOut = true
                }

                view.animate()
                        .setListener(if (swipedOut) destroyListener else null)
                        .x(offset)
                        .alpha(alpha)
                        .setDuration(duration)
                        .start()

                return true
            }

            else -> return false
        }
    }
}
