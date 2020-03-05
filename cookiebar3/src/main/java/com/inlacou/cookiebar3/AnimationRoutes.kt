package com.inlacou.cookiebar3

import android.view.Gravity
import android.view.animation.AccelerateDecelerateInterpolator

object AnimationRoutes {
	val showFromBottom by lazy { listOf(
			CookieAnimationStep(0, null, 0, CookieAnimation({
				//Basic initialization
				centerInParent(horizontal = true, vertical = false)
				invisible()
			})),
			CookieAnimationStep(0, null, 0, listOf(CookieAnimation({
				//Precise initialization
				visible()
				outOfScreen(Gravity.BOTTOM)
			}))),
			CookieAnimationStep(700, null, 3000, CookieAnimation({
				//Appear and anchor bottom
				bottomOfItsParent(0f)
			})),
			CookieAnimationStep(700, null, 0, CookieAnimation({
				//Move out of screen
				outOfScreen(Gravity.BOTTOM)
			}))) }
	val showFromTop by lazy { listOf(
			CookieAnimationStep(tag = "basic init", duration = 0, animation = CookieAnimation({
				//Basic initialization
				centerInParent(horizontal = true, vertical = false)
				invisible()
			})),
			CookieAnimationStep(tag = "precise init", duration = 0, animations = listOf(CookieAnimation({
				//Precise initialization
				visible()
				outOfScreen(Gravity.TOP)
			}))),
			CookieAnimationStep(tag = "appear and anchor", duration = 700, holdOnPosition = 3000, animation = CookieAnimation({
				//Appear and anchor bottom
				topOfItsParent(0f)
			})),
			CookieAnimationStep(duration = 700, animation = CookieAnimation({
				//Move out of screen
				outOfScreen(Gravity.TOP)
			}))) }
	val specialExample by lazy { listOf(
			CookieAnimationStep(tag = "basic init", duration = 0, animation = CookieAnimation({
				//Basic initialization
				centerInParent(horizontal = true, vertical = false)
				invisible()
			}), animation1 = CookieAnimation({
				textSize(36f)
			}, target = R.id.tv_title)),
			CookieAnimationStep(tag = "precise init", duration = 0, animations = listOf(CookieAnimation({
				//Precise initialization
				visible()
				outOfScreen(Gravity.BOTTOM)
			}), CookieAnimation({
				textSize(36f)
			}, target = R.id.tv_title))),
			CookieAnimationStep(tag = "appear, center and anchor", duration = 700, interpolator = AccelerateDecelerateInterpolator(), holdOnPosition = 5000, animation = CookieAnimation({
				//Appear and move to center
				centerInParent(horizontal = false, vertical = true)
			})),
			CookieAnimationStep(tag = "botton and resize, and anchor", duration = 700, holdOnPosition = 3000, animation = CookieAnimation({
				//Move to bottom and resize self
				width(505, keepRatio = true, toDp = true)
				bottomOfItsParent(60f)
			}), animation1 = CookieAnimation({
				textSize(30f)
			}, target = R.id.tv_title)),
			CookieAnimationStep(tag = "move out", duration = 700, animation = CookieAnimation({
				//Move out of screen
				outOfScreen(Gravity.BOTTOM)
			}))) }
}