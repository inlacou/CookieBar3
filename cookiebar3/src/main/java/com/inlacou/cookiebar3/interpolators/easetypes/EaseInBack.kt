package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseInBack : CubicBezier() {
	init {
		init(0.6, -0.28, 0.735, 0.045)
	}
}