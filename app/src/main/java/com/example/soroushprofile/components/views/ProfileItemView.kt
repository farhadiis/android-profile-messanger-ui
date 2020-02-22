package com.example.soroushprofile.components.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.View.OnClickListener
import android.widget.CompoundButton
import android.widget.FrameLayout
import com.example.soroushprofile.R
import kotlinx.android.synthetic.main.profile_item_view.view.*

class ProfileItemView : FrameLayout {

    var titleText: CharSequence?
        get() = title.text
        set(value) {
            title.text = value
            title.visibility = if (value == null) View.GONE else View.VISIBLE
        }


    var summaryText: CharSequence?
        get() = summary.text
        set(value) {
            summary.text = value
            summary.visibility = if (value == null) View.GONE else View.VISIBLE
        }

    var summaryTextColor: Int
        get() = title.currentTextColor
        set(value) {
            summary.setTextColor(value)
        }

    var titleTextColor: Int
        get() = title.currentTextColor
        set(value) {
            title.setTextColor(value)
        }

    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        inflate(context, R.layout.profile_item_view, this)
        setOnCheckedChangeListener(null)
        titleText = null
        summaryText = null
    }

    fun setOnCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener?) {
        switch_compat.visibility = if (listener == null) View.GONE else View.VISIBLE
        switch_compat.setOnCheckedChangeListener(listener)
        root.setOnClickListener(if (listener == null) null
        else OnClickListener { switch_compat.isChecked = !switch_compat.isChecked })
    }

}