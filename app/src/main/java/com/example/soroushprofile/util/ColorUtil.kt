package com.example.soroushprofile.util

import android.graphics.Color
import kotlin.math.min
import kotlin.math.round

object ColorUtil {

    /**
     * https://stackoverflow.com/questions/33072365/how-to-darken-a-given-color-int
     * @param color color provided
     * @param factor factor to make color darker
     * @return int as darker color
     */
    fun manipulateColor(color: Int, factor: Float): Int {
        val a: Int = Color.alpha(color)
        val r: Float = round(Color.red(color) * factor)
        val g: Float = round(Color.green(color) * factor)
        val b: Float = round(Color.blue(color) * factor)
        return Color.argb(a,
                min(r.toInt(), 255),
                min(g.toInt(), 255),
                min(b.toInt(), 255))
    }


}
