package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInOutBack : CubicBezier() {
	init {
		init(0.68, -0.55, 0.265, 1.55)
	}
}