package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInSine : CubicBezier() {
	init {
		init(0.47, 0.0, 0.745, 0.715)
	}
}