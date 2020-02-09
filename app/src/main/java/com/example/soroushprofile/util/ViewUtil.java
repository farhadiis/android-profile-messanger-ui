package com.example.soroushprofile.util;

import android.content.Context;
import android.view.Gravity;

import androidx.annotation.IdRes;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class ViewUtil {

    public static CoordinatorLayout.LayoutParams getCoordinatorLayoutParams(Context context, @IdRes int id, int marginRight) {
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(
                CoordinatorLayout.LayoutParams.MATCH_PARENT, CoordinatorLayout.LayoutParams.WRAP_CONTENT);
        int margin = (int) Util.convertDpToPixel(marginRight, context);
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT | Gravity.END;
        params.setAnchorId(id);
        params.setMargins(margin, margin, margin, margin);
        return params;
    }
}
