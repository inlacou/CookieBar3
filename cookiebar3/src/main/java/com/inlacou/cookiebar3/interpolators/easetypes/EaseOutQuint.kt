package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseOutQuint : CubicBezier() {
	init {
		init(0.23, 1.0, 0.32, 1.0)
	}
}