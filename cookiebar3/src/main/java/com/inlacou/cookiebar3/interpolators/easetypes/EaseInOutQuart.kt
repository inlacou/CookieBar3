package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInOutQuart : CubicBezier() {
	init {
		init(0.77, 0.0, 0.175, 1.0)
	}
}