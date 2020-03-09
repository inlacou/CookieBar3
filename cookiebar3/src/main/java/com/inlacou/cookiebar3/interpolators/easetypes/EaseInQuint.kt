package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInQuint : CubicBezier() {
	init {
		init(0.755, 0.05, 0.855, 0.06)
	}
}