package com.example.soroushprofile.prefrences.widgets;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

public class SoroushPreference extends Preference {

    private TextView mTitleView;

    @ColorInt
    private int textColor = Color.BLACK;

    public SoroushPreference(Context context) {
        super(context);
    }

    public SoroushPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SoroushPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SoroushPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);

        mTitleView = (TextView) holder.findViewById(android.R.id.title);

        setTitleColor(this.textColor);
    }

    public void setTitleColor(@ColorInt int color) {
        this.textColor = color;
        if (mTitleView == null) return;
        mTitleView.setTextColor(this.textColor);
    }
}
