package org.aviran.cookiebar2

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.app.Activity
import android.support.annotation.AnimRes
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
 *
 *
 * CookieBar
 * .build(MainActivity.this)
 * .setTitle("TITLE")
 * .setMessage("MESSAGE")
 * .show();
 */
class CookieBar private constructor(private val context: Activity, params: Params?) {

    private var cookieView: Cookie? = null

    val view: View?
        get() = cookieView

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
        if (cookieView != null) {
            val decorView = context.window.decorView as ViewGroup
            val content = decorView.findViewById<ViewGroup>(android.R.id.content)
            if (cookieView?.parent == null) {
                val parent = if (cookieView?.layoutGravity == Gravity.BOTTOM)
                    content
                else
                    decorView
                addCookie(parent, cookieView)
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

    private fun addCookie(parent: ViewGroup, cookie: Cookie) {
        if (cookie.parent != null) {
            return
        }

        // if exists, remove existing cookie
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            if (child is Cookie) {
                child.dismiss(object : Cookie.CookieBarDismissListener {
                    override fun onDismiss() {
                        parent.addView(cookie)
                    }
                })
                return
            }
        }

        parent.addView(cookie)
    }

    class Builder
    /**
     * Create a builder for an cookie.
     */
    internal constructor(private val context: Activity) {

        private val params = Params()

        fun setIcon(@DrawableRes iconResId: Int): Builder {
            params.iconResId = iconResId
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

        fun setDuration(duration: Long): Builder {
            params.duration = duration
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
        @Deprecated("As of CookieBar2 1.1.0, use\n" +
                "                      {@link #setCookiePosition(int)} instead.\n" +
                "\n" +
                "          ")
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

        fun setAnimationIn(@AnimRes topAnimation: Int, @AnimRes bottomAnimation: Int): Builder {
            params.animationInTop = topAnimation
            params.animationInBottom = bottomAnimation
            return this
        }

        fun setAnimationOut(@AnimRes topAnimation: Int, @AnimRes bottomAnimation: Int): Builder {
            params.animationOutTop = topAnimation
            params.animationOutBottom = bottomAnimation
            return this
        }


        fun setEnableAutoDismiss(enableAutoDismiss: Boolean): Builder {
            params.enableAutoDismiss = enableAutoDismiss
            return this
        }

        fun setSwipeToDismiss(enableSwipeToDismiss: Boolean): Builder {
            params.enableSwipeToDismiss = enableSwipeToDismiss
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
        var enableSwipeToDismiss = true
        var enableAutoDismiss = true
        var iconResId: Int = 0
        var backgroundColor: Int = 0
        var titleColor: Int = 0
        var messageColor: Int = 0
        var duration: Long = 2000
        var cookiePosition = Gravity.TOP
        var customViewResource: Int = 0
        var animationInTop = R.anim.slide_in_from_top
        var animationInBottom = R.anim.slide_in_from_bottom
        var animationOutTop = R.anim.slide_out_to_top
        var animationOutBottom = R.anim.slide_out_to_bottom
        var viewInitializer: CustomViewInitializer? = null
        var iconAnimator: AnimatorSet? = null
    }

    interface CustomViewInitializer {
        fun initView(view: View)
    }

    companion object {
        val TOP = Gravity.TOP
        val BOTTOM = Gravity.BOTTOM

        fun build(activity: Activity): Builder {
            return CookieBar.Builder(activity)
        }

        fun dismiss(activity: Activity) {
            CookieBar(activity, null)
        }
    }
}