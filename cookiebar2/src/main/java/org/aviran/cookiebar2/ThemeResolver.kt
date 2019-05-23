package org.aviran.cookiebar2

import android.content.Context
import android.content.res.TypedArray
import android.support.annotation.AttrRes

internal object ThemeResolver {

    @JvmOverloads
    fun getColor(context: Context, @AttrRes attr: Int, defaultColor: Int = 0): Int {
        val a = context.theme.obtainStyledAttributes(intArrayOf(attr))
        try {
            return a.getColor(0, defaultColor)
        } finally {
            a.recycle()
        }
    }

    fun getDimen(context: Context, @AttrRes attr: Int, defaultSize: Int): Int {
        val a = context.theme.obtainStyledAttributes(intArrayOf(attr))
        try {
            return a.getDimensionPixelSize(0, defaultSize)
        } finally {
            a.recycle()
        }
    }

}
