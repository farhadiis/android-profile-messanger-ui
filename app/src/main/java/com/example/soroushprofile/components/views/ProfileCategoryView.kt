package com.example.soroushprofile.components.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.soroushprofile.R
import kotlinx.android.synthetic.main.profile_category_view.view.*

class ProfileCategoryView : FrameLayout {

    var titleText: CharSequence?
        get() = title.text
        set(value) {
            title.text = value
            title.visibility = if (value == null) View.GONE else View.VISIBLE
        }


    var summaryText: CharSequence?
        get() = right_summary.text
        set(value) {
            right_summary.text = value
            right_summary.visibility = if (value == null) View.GONE else View.VISIBLE
        }

    constructor(context: Context, vararg items: ViewGroup) : super(context) {
        initialize(items.toList())
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initialize(null)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize(null)
    }

    private fun initialize(items: List<ViewGroup>?) {
        inflate(context, R.layout.profile_category_view, this)
        titleText = null
        summaryText = null
        items?.forEach { body.addView(it) }
    }

    fun setOnMoreClickListener(listener: OnClickListener?) {
        right_summary.setOnClickListener(listener)
    }
}