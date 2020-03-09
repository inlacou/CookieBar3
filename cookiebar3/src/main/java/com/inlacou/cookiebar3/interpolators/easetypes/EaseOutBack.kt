package com.inlacou.cookiebar3.interpolators.easetypes

import com.inlacou.cookiebar3.interpolators.CubicBezier

/**
 * Created by Weiping on 2016/3/3.
 */
class EaseOutBack : CubicBezier() {
	init {
		init(0.175, 0.885, 0.32, 1.275)
	}
}