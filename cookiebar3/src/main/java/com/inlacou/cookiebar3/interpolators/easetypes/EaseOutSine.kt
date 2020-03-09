package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseOutSine : CubicBezier() {
	init {
		init(0.39, 0.575, 0.565, 1.0)
	}
}