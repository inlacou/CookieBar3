package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseOutQuad : CubicBezier() {
	init {
		init(0.25, 0.46, 0.45, 0.94)
	}
}