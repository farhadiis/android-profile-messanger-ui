package com.example.soroushprofile.prefrences.widgets

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.View
import android.widget.TextView

import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceViewHolder

import com.example.soroushprofile.R

class SoroushPreferenceCategory : PreferenceCategory {

    private var rightSummary: TextView? = null
    private var mSummary: CharSequence? = null
    private var mSummaryClickListener: View.OnClickListener? = null

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context) : super(context) {
        initialize()
    }

    fun setOnSummaryClickListener(summaryClickListener: View.OnClickListener) {
        mSummaryClickListener = summaryClickListener
    }

    private fun initialize() {
        layoutResource = R.layout.preference_right_summary_widget
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        rightSummary = holder.findViewById(R.id.right_summary) as TextView
        setSummary(mSummary)
    }

    override fun setSummary(summary: CharSequence?) {
        super.setSummary(null)
        this.mSummary = summary

        if (this.rightSummary != null) {
            this.rightSummary!!.text = summary
            this.rightSummary!!.setOnClickListener(mSummaryClickListener)
            this.rightSummary!!.visibility = if (TextUtils.isEmpty(summary)) View.GONE else View.VISIBLE
        }
    }
}
