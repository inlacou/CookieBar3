package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInOutQuint : CubicBezier() {
	init {
		init(0.86, 0.0, 0.07, 1.0)
	}
}