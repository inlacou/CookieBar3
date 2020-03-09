package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseOutQuart : CubicBezier() {
	init {
		init(0.165, 0.84, 0.44, 1.0)
	}
}