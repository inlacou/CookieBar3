package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInOutExpo : CubicBezier() {
	init {
		init(1f, 0f, 0f, 1f)
	}
}