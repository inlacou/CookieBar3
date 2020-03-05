package com.inlacou.cookiebar3

import android.support.annotation.IdRes
import android.view.animation.Interpolator
import com.github.florent37.kotlin.pleaseanimate.core.Expectations

data class CookieAnimation(
		val expectations: (Expectations.() -> Unit),
		@IdRes val target: Int? = null)