package com.inlacou.cookiebar3

import android.view.Gravity
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator

object AnimationRoutes {
	
	private const val DEFAULT_TIME_CENTER_OF_SCREEN = 5000L
	private const val DEFAULT_TIME_ABOVE_BOTTOM_BAR = 3000L
	private const val DEFAULT_TIME_MOVEMENT_ANIMATION = 700L
	private const val DEFAULT_BOTTOM_MARGIN_ABOVE_BOTTOM_BAR = 130f
	
	val showFromBottom by lazy { listOf(
			CookieAnimationStep(tag = "basic init", duration = 0, animation = CookieAnimation({
				//Basic initialization
				centerInParent(horizontal = true, vertical = false)
				invisible()
			})),
			CookieAnimationStep(tag = "precise init", duration = 0, animations = listOf(CookieAnimation({
				//Precise initialization
				visible()
				outOfScreen(Gravity.BOTTOM)
			}))),
			CookieAnimationStep(tag = "appear and anchor", duration = DEFAULT_TIME_MOVEMENT_ANIMATION, interpolator = BounceInterpolator(), holdOnPosition = DEFAULT_TIME_ABOVE_BOTTOM_BAR, animation = CookieAnimation({
				//Appear and anchor bottom
				bottomOfItsParent(DEFAULT_BOTTOM_MARGIN_ABOVE_BOTTOM_BAR)
			})),
			CookieAnimationStep(tag = "move out", duration = DEFAULT_TIME_MOVEMENT_ANIMATION, animation = CookieAnimation({
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
			CookieAnimationStep(tag = "appear and anchor", duration = DEFAULT_TIME_MOVEMENT_ANIMATION, interpolator = BounceInterpolator(), holdOnPosition = DEFAULT_TIME_ABOVE_BOTTOM_BAR, animation = CookieAnimation({
				//Appear and anchor bottom
				topOfItsParent(DEFAULT_BOTTOM_MARGIN_ABOVE_BOTTOM_BAR)
			})),
			CookieAnimationStep(tag = "move out", duration = DEFAULT_TIME_MOVEMENT_ANIMATION, animation = CookieAnimation({
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
			CookieAnimationStep(tag = "appear, center and anchor", duration = DEFAULT_TIME_MOVEMENT_ANIMATION, interpolator = AccelerateDecelerateInterpolator(), holdOnPosition = DEFAULT_TIME_CENTER_OF_SCREEN, animation = CookieAnimation({
				//Appear and move to center
				centerInParent(horizontal = false, vertical = true)
			})),
			CookieAnimationStep(tag = "botton and resize, and anchor", duration = DEFAULT_TIME_MOVEMENT_ANIMATION, interpolator = AccelerateDecelerateInterpolator(), holdOnPosition = DEFAULT_TIME_ABOVE_BOTTOM_BAR, animation = CookieAnimation({
				//Move to bottom and resize self
				width(505, keepRatio = true, toDp = true)
				bottomOfItsParent(DEFAULT_BOTTOM_MARGIN_ABOVE_BOTTOM_BAR)
			}), animation1 = CookieAnimation({
				textSize(30f)
			}, target = R.id.tv_title)),
			CookieAnimationStep(tag = "move out", duration = DEFAULT_TIME_MOVEMENT_ANIMATION, animation = CookieAnimation({
				//Move out of screen
				outOfScreen(Gravity.BOTTOM)
			}))) }
}
