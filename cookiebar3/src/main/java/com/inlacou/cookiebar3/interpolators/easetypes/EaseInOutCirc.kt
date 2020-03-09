package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInOutCirc : CubicBezier() {
	init {
		init(0.785, 0.135, 0.15, 0.86)
	}
}