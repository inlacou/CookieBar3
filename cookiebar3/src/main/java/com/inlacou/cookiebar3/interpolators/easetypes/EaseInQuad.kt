package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInQuad : CubicBezier() {
	init {
		init(0.55, 0.085, 0.68, 0.53)
	}
}