package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInOutSine : CubicBezier() {
	init {
		init(0.445, 0.05, 0.55, 0.95)
	}
}