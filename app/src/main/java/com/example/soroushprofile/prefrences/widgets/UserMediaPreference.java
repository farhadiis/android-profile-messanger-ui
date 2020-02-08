package com.example.soroushprofile.prefrences.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soroushprofile.R;
import com.example.soroushprofile.models.User;
import com.example.soroushprofile.userprofile.UserMediaProfileAdapter;

public class UserMediaPreference extends Preference {

    private User user;

    private RecyclerView mRecyclerView;
    private TextView mNoContentTextView;


    public UserMediaPreference(Context context) {
        super(context);
        initialize();
    }

    public UserMediaPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public UserMediaPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public UserMediaPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }


    private void initialize() {
        setLayoutResource(R.layout.user_media_preference_view);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);

        mRecyclerView = (holder.itemView).findViewById(R.id.recycler_view);
        mNoContentTextView = (holder.itemView).findViewById(R.id.no_content);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        UserMediaProfileAdapter adapter = new UserMediaProfileAdapter(null);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        refresh(user);
    }

    public void refresh(User user) {
        this.user = user;
        if (user.hasContent()) {
            if (mRecyclerView == null) return;
            mRecyclerView.setVisibility(View.VISIBLE);
            mNoContentTextView.setVisibility(View.GONE);
        } else {
            if (mNoContentTextView == null) return;
            mNoContentTextView.setVisibility(View.VISIBLE);
            mNoContentTextView.setText(getContext()
                    .getString(R.string.profile_user_media_no_media_individual, user.getName()));
            mRecyclerView.setVisibility(View.GONE);

        }
    }
}
