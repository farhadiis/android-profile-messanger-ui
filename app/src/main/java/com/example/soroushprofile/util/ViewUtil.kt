package com.example.soroushprofile.util

import android.content.Context
import android.view.Gravity

import androidx.annotation.IdRes
import androidx.coordinatorlayout.widget.CoordinatorLayout

object ViewUtil {

    fun getCoordinatorLayoutParams(context: Context, @IdRes id: Int, marginRight: Int): CoordinatorLayout.LayoutParams {
        val params = CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT)
        val margin = Util.convertDpToPixel(marginRight.toFloat(), context).toInt()
        params.anchorGravity = Gravity.BOTTOM or Gravity.RIGHT or Gravity.END
        params.anchorId = id
        params.setMargins(margin, margin, margin, margin)
        return params
    }
}
