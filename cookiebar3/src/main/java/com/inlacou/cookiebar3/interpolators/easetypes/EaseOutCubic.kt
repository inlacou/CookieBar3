package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseOutCubic : CubicBezier() {
	init {
		init(0.215, 0.61, 0.355, 1.0)
	}
}