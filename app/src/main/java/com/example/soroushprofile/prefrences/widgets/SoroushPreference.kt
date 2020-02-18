package com.example.soroushprofile.prefrences.widgets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.TextView

import androidx.annotation.ColorInt
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder

class SoroushPreference : Preference {

    private var mTitleView: TextView? = null

    @ColorInt
    private var textColor = Color.BLACK

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {}


    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)

        mTitleView = holder.findViewById(android.R.id.title) as TextView

        setTitleColor(this.textColor)
    }

    fun setTitleColor(@ColorInt color: Int) {
        this.textColor = color
        if (mTitleView == null) return
        mTitleView!!.setTextColor(this.textColor)
    }
}
