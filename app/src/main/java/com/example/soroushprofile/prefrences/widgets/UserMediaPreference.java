package com.example.soroushprofile.prefrences.widgets;

import android.content.Context;
import android.util.AttributeSet;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soroushprofile.R;

public class UserMediaPreference extends Preference {


    private RecyclerView mRecyclerView;


    public UserMediaPreference(Context context) {
        super(context);
        init();
    }

    public UserMediaPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserMediaPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public UserMediaPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init() {
        setLayoutResource(R.layout.user_media_preference_view);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);

        mRecyclerView = (holder.itemView).findViewById(R.id.recycler_view);

        bind();
    }

    private void bind() {
    }
}
