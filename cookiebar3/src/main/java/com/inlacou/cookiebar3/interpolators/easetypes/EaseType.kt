package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.Interpolable

/**
 * Created by Weiping on 03/03/2016.
 * Updated by Inlacou on 27/11/2019.
 * Visual representation here: https://easings.net/en
 */
enum class EaseType

/**
 * ease animation helps to make the movement more real
 * @param easingType
 */
constructor(private val easingType: Class<out Interpolable>) {
	None(com.inlacou.cookiebar3.interpolators.easetypes.NoEase::class.java),

	EaseInSine(com.inlacou.cookiebar3.interpolators.easetypes.EaseInSine::class.java),
	EaseOutSine(com.inlacou.cookiebar3.interpolators.easetypes.EaseOutSine::class.java),
	EaseInOutSine(com.inlacou.cookiebar3.interpolators.easetypes.EaseInOutSine::class.java),

	EaseInQuad(com.inlacou.cookiebar3.interpolators.easetypes.EaseInQuad::class.java),
	EaseOutQuad(com.inlacou.cookiebar3.interpolators.easetypes.EaseOutQuad::class.java),
	EaseInOutQuad(com.inlacou.cookiebar3.interpolators.easetypes.EaseInOutQuad::class.java),

	EaseInCubic(com.inlacou.cookiebar3.interpolators.easetypes.EaseInCubic::class.java),
	EaseOutCubic(com.inlacou.cookiebar3.interpolators.easetypes.EaseOutCubic::class.java),
	EaseInOutCubic(com.inlacou.cookiebar3.interpolators.easetypes.EaseInOutCubic::class.java),

	EaseInQuart(com.inlacou.cookiebar3.interpolators.easetypes.EaseInQuart::class.java),
	EaseOutQuart(com.inlacou.cookiebar3.interpolators.easetypes.EaseOutQuart::class.java),
	EaseInOutQuart(com.inlacou.cookiebar3.interpolators.easetypes.EaseInOutQuart::class.java),

	EaseInQuint(com.inlacou.cookiebar3.interpolators.easetypes.EaseInQuint::class.java),
	EaseOutQuint(com.inlacou.cookiebar3.interpolators.easetypes.EaseOutQuint::class.java),
	EaseInOutQuint(com.inlacou.cookiebar3.interpolators.easetypes.EaseInOutQuint::class.java),

	EaseInExpo(com.inlacou.cookiebar3.interpolators.easetypes.EaseInExpo::class.java),
	EaseOutExpo(com.inlacou.cookiebar3.interpolators.easetypes.EaseOutExpo::class.java),
	EaseInOutExpo(com.inlacou.cookiebar3.interpolators.easetypes.EaseInOutExpo::class.java),

	EaseInCirc(com.inlacou.cookiebar3.interpolators.easetypes.EaseInCirc::class.java),
	EaseOutCirc(com.inlacou.cookiebar3.interpolators.easetypes.EaseOutCirc::class.java),
	EaseInOutCirc(com.inlacou.cookiebar3.interpolators.easetypes.EaseInOutCirc::class.java),

	EaseInBack(com.inlacou.cookiebar3.interpolators.easetypes.EaseInBack::class.java),
	EaseOutBack(com.inlacou.cookiebar3.interpolators.easetypes.EaseOutBack::class.java),
	EaseInOutBack(com.inlacou.cookiebar3.interpolators.easetypes.EaseInOutBack::class.java),

	EaseInElastic(com.inlacou.cookiebar3.interpolators.easetypes.EaseInElastic::class.java),
	EaseOutElastic(com.inlacou.cookiebar3.interpolators.easetypes.EaseOutElastic::class.java),
	EaseInOutElastic(com.inlacou.cookiebar3.interpolators.easetypes.EaseInOutElastic::class.java),

	EaseInBounce(com.inlacou.cookiebar3.interpolators.easetypes.EaseInBounce::class.java),
	EaseOutBounce(com.inlacou.cookiebar3.interpolators.easetypes.EaseOutBounce::class.java),
	EaseInOutBounce(com.inlacou.cookiebar3.interpolators.easetypes.EaseInOutBounce::class.java),

	Linear(com.inlacou.cookiebar3.interpolators.easetypes.Linear::class.java);
	
	fun newInstance() = easingType.newInstance()
}
