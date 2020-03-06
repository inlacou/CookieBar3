package com.inlacou.cookiebar3

import android.view.animation.Interpolator

data class CookieAnimationStep constructor(
		val duration: Long,
		val interpolator: Interpolator? = null,
		/**
		 * If holdOnPosition > 0, a new step is created to stay in the same place.
		 */
		val holdOnPosition: Long = 0,
		val animations: List<CookieAnimation>,
		val tag: String? = null
){
	constructor(duration: Long, interpolator: Interpolator? = null, holdOnPosition: Long = 0, animation: CookieAnimation, tag: String? = null) : this(duration, interpolator, holdOnPosition, listOf(animation), tag)
	constructor(duration: Long, interpolator: Interpolator? = null, holdOnPosition: Long = 0, animation: CookieAnimation, animation1: CookieAnimation, tag: String? = null) : this(duration, interpolator, holdOnPosition, listOf(animation, animation1), tag)
	constructor(duration: Long, interpolator: Interpolator? = null, holdOnPosition: Long = 0, animation: CookieAnimation, animation1: CookieAnimation, animation2: CookieAnimation, tag: String? = null) : this(duration, interpolator, holdOnPosition, listOf(animation, animation1, animation2), tag)
}
