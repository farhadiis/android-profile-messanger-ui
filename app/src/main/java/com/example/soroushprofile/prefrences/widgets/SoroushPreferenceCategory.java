package com.example.soroushprofile.prefrences.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceViewHolder;

import com.example.soroushprofile.R;

public class SoroushPreferenceCategory extends PreferenceCategory {

    private TextView rightSummary;
    private CharSequence summary;
    private View.OnClickListener mSummaryClickListener;

    public SoroushPreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    public SoroushPreferenceCategory(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public SoroushPreferenceCategory(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public SoroushPreferenceCategory(Context context) {
        super(context);
        initialize();
    }

    public void setOnSummaryClickListener(View.OnClickListener summaryClickListener) {
        mSummaryClickListener = summaryClickListener;
    }

    private void initialize() {
        setLayoutResource(R.layout.preference_right_summary_widget);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        rightSummary = (TextView) holder.findViewById(R.id.right_summary);
        setSummary(summary);
    }

    @Override
    public void setSummary(CharSequence summary) {
        super.setSummary(null);
        this.summary = summary;

        if (this.rightSummary != null) {
            this.rightSummary.setText(summary);
            this.rightSummary.setOnClickListener(mSummaryClickListener);
            this.rightSummary.setVisibility(TextUtils.isEmpty(summary) ? View.GONE : View.VISIBLE);
        }
    }
}
