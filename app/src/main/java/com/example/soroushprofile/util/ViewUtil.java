package com.example.soroushprofile.util;

import android.content.Context;
import android.widget.LinearLayout;

public class ViewUtil {

    public static LinearLayout.LayoutParams getParams(Context context, int marginDp) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int margin = (int) Util.convertDpToPixel(marginDp, context);
        params.setMargins(margin, margin, margin, margin);
        return params;
    }
}
