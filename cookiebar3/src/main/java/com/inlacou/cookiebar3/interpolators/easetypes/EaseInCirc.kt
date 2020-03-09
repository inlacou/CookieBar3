package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInCirc : CubicBezier() {
	init {
		init(0.6, 0.04, 0.98, 0.335)
	}
}