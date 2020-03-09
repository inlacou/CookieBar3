package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInCubic : CubicBezier() {
	init {
		init(0.55, 0.055, 0.675, 0.19)
	}
}