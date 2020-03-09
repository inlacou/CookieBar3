package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseOutExpo : CubicBezier() {
	init {
		init(0.19, 1.0, 0.22, 1.0)
	}
}