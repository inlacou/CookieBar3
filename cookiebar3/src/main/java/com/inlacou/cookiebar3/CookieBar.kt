package com.inlacou.cookiebar3

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.app.Activity
import android.support.annotation.AnimatorRes
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.view.Gravity
import android.view.View
import android.view.ViewGroup

/**
 * CookieBar is a lightweight library for showing a brief message at the top or bottom of the
 * screen.
 */
class CookieBar private constructor(private val context: Activity, params: Params?) {

    private var cookieView: Cookie? = null

    init {
        if (params == null) {
            // since params is null, this CookieBar object can only be used to dismiss
            // existing cookies
            dismiss()
        }else {
            cookieView = Cookie(context)
            cookieView?.setParams(params)
        }
    }

    private fun show() {
        cookieView?.let { cookie ->
            val decorView = context.window.decorView as ViewGroup
            if (cookie.parent == null) {
                val parent =
                        if (cookie.layoutGravity==Gravity.BOTTOM) decorView.findViewById(android.R.id.content)
                        else decorView
                addCookieAndReplacePresent(parent, cookie)
            }
        }
    }

    private fun dismiss() {
        val decorView = context.window.decorView as ViewGroup
        val content = decorView.findViewById<ViewGroup>(android.R.id.content)

        removeFromParent(decorView)
        removeFromParent(content)
    }

    private fun removeFromParent(parent: ViewGroup) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            if (child is Cookie) {
                child.dismiss()
                return
            }
        }

    }
    
    /**
     * Adds a cookie and removes previous cookies on the same position (TOP or BOTTOM).
     */
    private fun addCookieAndReplacePresent(parent: ViewGroup, cookie: Cookie) {
        if (cookie.parent != null) return
        
        // if exists, remove existing cookie
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            if (child is Cookie) {
                child.dismiss { parent.addView(cookie) }
                return
            }
        }

        parent.addView(cookie)
    }

    class Builder internal constructor(private val context: Activity) {

        private val params = Params()

        fun setIcon(@DrawableRes iconResId: Int): Builder {
            params.iconResId = iconResId
            return this
        }

        fun maybeSetIcon(@DrawableRes iconResId: Int?): Builder {
            if(iconResId!=null) params.iconResId = iconResId
            return this
        }

        fun setTitle(title: String): Builder {
            params.title = title
            return this
        }

        fun setTitle(@StringRes resId: Int): Builder {
            params.title = context.getString(resId)
            return this
        }

        fun setMessage(message: String): Builder {
            params.message = message
            return this
        }

        fun setMessage(@StringRes resId: Int): Builder {
            params.message = context.getString(resId)
            return this
        }

        fun setTitleColor(@ColorRes titleColor: Int): Builder {
            params.titleColor = titleColor
            return this
        }

        fun setMessageColor(@ColorRes messageColor: Int): Builder {
            params.messageColor = messageColor
            return this
        }

        fun setBackgroundColor(@ColorRes backgroundColor: Int): Builder {
            params.backgroundColor = backgroundColor
            return this
        }

        fun setIconAnimation(@AnimatorRes iconAnimation: Int): Builder {
            params.iconAnimator = AnimatorInflater.loadAnimator(context, iconAnimation) as AnimatorSet
            return this
        }

        /**
         * Sets cookie position
         *
         * @param layoutGravity Cookie position, use either CookieBar.TOP or CookieBar.BOTTOM
         * @return builder
         */
        @Deprecated("As of cookiebar3 1.1.0, use {@link #setCookiePosition(int)} instead.", ReplaceWith("@link setCookiePosition(layoutGravity)"))
        fun setLayoutGravity(layoutGravity: Int): Builder {
            return setCookiePosition(layoutGravity)
        }


        /**
         * Sets cookie position
         *
         * @param cookiePosition Cookie position, use either CookieBar.TOP or CookieBar.BOTTOM
         * @return builder
         */
        fun setCookiePosition(cookiePosition: Int): Builder {
            params.cookiePosition = cookiePosition
            return this
        }


        fun setCustomView(@LayoutRes customView: Int): Builder {
            params.customViewResource = customView
            return this
        }

        fun setCustomViewInitializer(viewInitializer: CustomViewInitializer): Builder {
            params.viewInitializer = viewInitializer
            return this
        }

        fun setShownListener(animationEndListener: ((animationIndex: Int, tag: String?, hold: Boolean) -> Unit)): Builder {
            params.animationEndListener = animationEndListener
            return this
        }

        fun setDismissListener(dismissListener: (() -> Unit)): Builder {
            params.dismissListener = dismissListener
            return this
        }

        fun setSteps(additionalSteps: List<CookieAnimationStep>): Builder {
            params.steps = additionalSteps.toMutableList()
            return this
        }

        fun addStep(additionalStep: CookieAnimationStep): Builder {
            params.steps.add(additionalStep)
            return this
        }

        fun create(): CookieBar {
            return CookieBar(context, params)
        }

        fun show(): CookieBar {
            val cookie = create()
            cookie.show()
            return cookie
        }
    }

    internal class Params {
        var title: String? = null
        var message: String? = null
        var iconResId: Int = 0
        var backgroundColor: Int = 0
        var titleColor: Int = 0
        var messageColor: Int = 0
        var cookiePosition = Gravity.TOP
        var customViewResource: Int = 0
        var steps = mutableListOf<CookieAnimationStep>()
        var viewInitializer: CustomViewInitializer? = null
        var iconAnimator: AnimatorSet? = null
        var dismissListener: (() -> Unit)? = null
        var animationEndListener: ((animationIndex: Int, tag: String?, hold: Boolean) -> Unit)? = null
    }

    interface CustomViewInitializer {
        fun initView(view: View)
    }

    companion object {
        /**
         * Default cookie position
         */
        const val TOP = Gravity.TOP
        const val BOTTOM = Gravity.BOTTOM

        fun build(activity: Activity): Builder {
            return Builder(activity)
        }

        fun dismiss(activity: Activity) {
            CookieBar(activity, null)
        }
    }
}