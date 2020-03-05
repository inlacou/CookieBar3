package com.inlacou.cookiebar3

import android.support.annotation.IdRes
import android.view.animation.Interpolator
import com.github.florent37.kotlin.pleaseanimate.core.Expectations

class AnimationStep constructor(val duration: Long, val interpolator: Interpolator? = null, val holdOnPosition: Long = 0, val animations: List<CookieAnimation>){
	constructor(duration: Long, interpolator: Interpolator?, holdOnPosition: Long, animation: CookieAnimation) : this(duration, interpolator, holdOnPosition, listOf(animation))
}

open class CookieAnimation(val expectations: (Expectations.() -> Unit), @IdRes val target: Int? = null)
class CookieStartAnimation(animation: (Expectations.() -> Unit)): CookieAnimation(animation)
class CookieEndAnimation(animation: (Expectations.() -> Unit)): CookieAnimation(animation)