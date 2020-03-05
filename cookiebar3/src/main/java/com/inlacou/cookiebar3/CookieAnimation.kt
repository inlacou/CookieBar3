package com.inlacou.cookiebar3

import android.support.annotation.IdRes
import android.view.animation.Interpolator
import com.github.florent37.kotlin.pleaseanimate.core.Expectations

class AnimationStep(val duration: Long, val interpolator: Interpolator? = null, vararg val animations: CookieAnimation)

open class CookieAnimation(val expectations: (Expectations.() -> Unit), @IdRes val target: Int? = null)
class CookieStartAnimation(animation: (Expectations.() -> Unit)): CookieAnimation(animation)
class CookieEndAnimation(animation: (Expectations.() -> Unit)): CookieAnimation(animation)