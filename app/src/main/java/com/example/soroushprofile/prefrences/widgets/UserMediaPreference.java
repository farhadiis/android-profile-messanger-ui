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
import com.example.soroushprofile.models.ConversationThread;
import com.example.soroushprofile.models.IndividualConversation;
import com.example.soroushprofile.userprofile.UserMediaProfileAdapter;

public class UserMediaPreference extends Preference {

    private ConversationThread thread;

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
        setLayoutResource(R.layout.preference_user_media_widget);
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


        refresh(thread);
    }

    public void refresh(ConversationThread thread) {
        this.thread = thread;
        if (thread.getMedia().isEmpty()) {
            if (mNoContentTextView == null) return;
            mNoContentTextView.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            setPlaceholder(thread);
        } else {
            if (mRecyclerView == null) return;
            mRecyclerView.setVisibility(View.VISIBLE);
            mNoContentTextView.setVisibility(View.GONE);
            setMedia(thread);
        }
    }

    private void setMedia(ConversationThread thread) {
    }

    private void setPlaceholder(ConversationThread thread) {
        String placeholder;
        switch (thread.getType()) {
            case individual:
                IndividualConversation conversation = (IndividualConversation) thread;
                placeholder = getContext().getString(R.string.profile_user_media_no_media_individual,
                        conversation.getUser().getName());
                break;
            case group:
                placeholder = getContext().getString(R.string.profile_user_media_no_media_group);
                break;
            case channel:
                placeholder = getContext().getString(R.string.profile_user_media_no_media_channel);
                break;
            default:
                throw new IllegalArgumentException("invalid argument");
        }
        mNoContentTextView.setText(placeholder);
    }
}
