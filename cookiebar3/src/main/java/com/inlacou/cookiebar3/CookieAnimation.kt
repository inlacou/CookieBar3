package com.inlacou.cookiebar3

import android.support.annotation.AnimRes

open class CookieAnimation(@AnimRes val animationId: Int, val duration: Long? = null, val delayUntilNextStep: Long = 0)
class CookieStartAnimation(@AnimRes animationId: Int, duration: Long? = null, waitUntilNextStep: Long = 2000): CookieAnimation(animationId, duration, waitUntilNextStep)
class CookieEndAnimation(@AnimRes animationId: Int, duration: Long? = null): CookieAnimation(animationId, duration, 0)