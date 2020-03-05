package com.inlacou.cookiebar3

import android.view.Gravity
import android.view.animation.AccelerateDecelerateInterpolator

object AnimationRoutes {
	val showFromBottom by lazy { listOf(
			AnimationStep(0, null, 0, CookieAnimation({
				//Basic initialization
				centerInParent(horizontal = true, vertical = false)
				invisible()
			})),
			AnimationStep(0, null, 0, listOf(CookieAnimation({
				//Precise initialization
				visible()
				outOfScreen(Gravity.BOTTOM)
			}))),
			AnimationStep(700, null, 3000, CookieAnimation({
				//Appear and anchor bottom
				bottomOfItsParent(0f)
			})),
			AnimationStep(700, null, 0, CookieAnimation({
				//Move out of screen
				outOfScreen(Gravity.BOTTOM)
			}))) }
	val showFromTop by lazy { listOf(
			AnimationStep(0, null, 0, CookieAnimation({
				//Basic initialization
				centerInParent(horizontal = true, vertical = false)
				invisible()
			})),
			AnimationStep(0, null, 0, listOf(CookieAnimation({
				//Precise initialization
				visible()
				outOfScreen(Gravity.TOP)
			}))),
			AnimationStep(700, null, 3000, CookieAnimation({
				//Appear and anchor bottom
				topOfItsParent(0f)
			})),
			AnimationStep(700, null, 0, CookieAnimation({
				//Move out of screen
				outOfScreen(Gravity.TOP)
			}))) }
	val specialExample by lazy { listOf(
			AnimationStep(0, null, 0, CookieAnimation({
				//Basic initialization
				centerInParent(horizontal = true, vertical = false)
				invisible()
			}), CookieAnimation({
				textSize(36f)
			}, target = R.id.tv_title)),
			AnimationStep(0, null, 0, listOf(CookieAnimation({
				//Precise initialization
				visible()
				outOfScreen(Gravity.BOTTOM)
			}), CookieAnimation({
				textSize(36f)
			}, target = R.id.tv_title))),
			AnimationStep(700, AccelerateDecelerateInterpolator(), 5000, CookieAnimation({
				//Appear and move to center
				centerInParent(horizontal = false, vertical = true)
			})),
			AnimationStep(700, null, 3000, CookieAnimation({
				//Move to bottom and resize self
				width(505, keepRatio = true, toDp = true)
				bottomOfItsParent(60f)
			}), CookieAnimation({
				textSize(30f)
			}, target = R.id.tv_title)),
			AnimationStep(700, null, 0, CookieAnimation({
				//Move out of screen
				outOfScreen(Gravity.BOTTOM)
			}))) }
}