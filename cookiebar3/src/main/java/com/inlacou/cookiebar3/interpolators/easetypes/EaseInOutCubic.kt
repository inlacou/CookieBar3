package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInOutCubic : CubicBezier() {
	init {
		init(0.645, 0.045, 0.355, 1.0)
	}
}