package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseOutCirc : CubicBezier() {
	init {
		init(0.075, 0.82, 0.165, 1.0)
	}
}