package com.example.soroushprofile;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;

import com.example.soroushprofile.avatar.ProfileAvatar;
import com.example.soroushprofile.models.ChannelConversation;
import com.example.soroushprofile.models.ConversationThread;
import com.example.soroushprofile.models.GroupConversation;
import com.example.soroushprofile.models.IndividualConversation;
import com.example.soroushprofile.userprofile.ProfileViewModel;
import com.example.soroushprofile.util.ColorUtil;
import com.example.soroushprofile.util.ViewUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ProfileActivity extends AppCompatActivity {

    private final static String TAG = ProfileActivity.class.getSimpleName();
    public final static String CONVERSATION_TYPE = "conversation_type";

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mHeaderImageView;
    private ImageView mAvatarImageView;
    private TextView mUsernameTextView;
    private TextView mStatusTextView;
    private LinearLayout mFabContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeRes();
        initializeToolbar();
        bindViewModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.more_settings:
                Toast.makeText(this, "More Option Trigger...", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeRes() {
        mCollapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        mHeaderImageView = findViewById(R.id.header_image_view);
        mAvatarImageView = findViewById(R.id.avatar_image_view);
        mUsernameTextView = findViewById(R.id.username);
        mStatusTextView = findViewById(R.id.status);
        mFabContainer = findViewById(R.id.fab_container);
    }

    private void initializeToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void bindViewModel() {
        ProfileViewModel model = new ViewModelProvider(this).get(ProfileViewModel.class);
        String key = getIntent().getStringExtra(CONVERSATION_TYPE);
        model.getConversationThread(key).observe(this, this::bindThread);
    }


    private void initializeProfileAvatar(ConversationThread thread) {
        ProfileAvatar.of(this, thread, mAvatarImageView, mHeaderImageView, this::initializeColorPalette);
    }

    private void initializeColorPalette(Palette palette) {
        int colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(this, R.color.colorPrimaryDark);
        if (palette != null) {
            int vibrantColor = palette.getMutedColor(colorPrimary);
            mCollapsingToolbarLayout.setContentScrimColor(vibrantColor);
            int darkVibrantColor = ColorUtil.manipulateColor(vibrantColor, 0.8f);
            mCollapsingToolbarLayout.setStatusBarScrimColor(darkVibrantColor);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        } else {
            mCollapsingToolbarLayout.setContentScrimColor(colorPrimary);
            mCollapsingToolbarLayout.setStatusBarScrimColor(colorPrimaryDark);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(colorPrimaryDark);
            }
        }
    }

    private void bindThread(ConversationThread thread) {
        initializeProfileAvatar(thread);
        mUsernameTextView.setText(thread.getTitle());
        switch (thread.getType()) {
            case individual:
                bindIndividual((IndividualConversation) thread);
                break;
            case group:
                bindGroup((GroupConversation) thread);
                break;
            case channel:
                bindChannel((ChannelConversation) thread);
                break;
        }
    }

    private void bindChannel(ChannelConversation conversation) {
        String subscribers = getResources().getQuantityString(R.plurals.profile_status_channel,
                conversation.getSubscriber(), conversation.getSubscriber());
        mStatusTextView.setText(subscribers);

        FloatingActionButton fabVoiceCall = (FloatingActionButton) getLayoutInflater()
                .inflate(R.layout.profile_fab_item, null);
        fabVoiceCall.setOnClickListener(new FabClickListener(FabClickListener.SHARE_CHANNEL));
        fabVoiceCall.setImageResource(R.drawable.ic_share_white_24dp);

        LinearLayout.LayoutParams params = ViewUtil.getParams(this, 6);
        mFabContainer.addView(fabVoiceCall, params);

    }

    private void bindGroup(GroupConversation conversation) {
        String members = getResources().getQuantityString(R.plurals.profile_status_group,
                conversation.getMember(), conversation.getMember());
        mStatusTextView.setText(members);

        FloatingActionButton fabVoiceCall = (FloatingActionButton) getLayoutInflater()
                .inflate(R.layout.profile_fab_item, null);
        fabVoiceCall.setOnClickListener(new FabClickListener(FabClickListener.ADD_TO_GROUP));
        fabVoiceCall.setImageResource(R.drawable.ic_group_add_white_24dp);

        LinearLayout.LayoutParams params = ViewUtil.getParams(this, 6);
        mFabContainer.addView(fabVoiceCall, params);
    }

    private void bindIndividual(IndividualConversation conversation) {
        mStatusTextView.setText(R.string.profile_status_online_user);

        FloatingActionButton fabVoiceCall = (FloatingActionButton) getLayoutInflater()
                .inflate(R.layout.profile_fab_item, null);
        FloatingActionButton fabVideoCall = (FloatingActionButton) getLayoutInflater()
                .inflate(R.layout.profile_fab_item, null);

        fabVideoCall.setOnClickListener(new FabClickListener(FabClickListener.VIDEO_CALL));
        fabVoiceCall.setOnClickListener(new FabClickListener(FabClickListener.VOICE_CALL));

        fabVideoCall.setImageResource(R.drawable.ic_video_call_white_24dp);
        fabVoiceCall.setImageResource(R.drawable.ic_call_white_24dp);


        LinearLayout.LayoutParams params = ViewUtil.getParams(this, 6);
        mFabContainer.addView(fabVoiceCall, params);
        mFabContainer.addView(fabVideoCall, params);
    }

    private final static class FabClickListener implements View.OnClickListener {
        private final int target;

        private static final int VIDEO_CALL = 0;
        private static final int VOICE_CALL = 1;
        private static final int ADD_TO_GROUP = 2;
        private static final int SHARE_CHANNEL = 3;

        private FabClickListener(int target) {
            this.target = target;
        }

        @Override
        public void onClick(View v) {
            switch (target) {
                case VIDEO_CALL:
                    Toast.makeText(v.getContext(), "Video Call Trigger...", Toast.LENGTH_SHORT).show();
                    break;
                case VOICE_CALL:
                    Toast.makeText(v.getContext(), "Voice Call Trigger...", Toast.LENGTH_SHORT).show();
                    break;
                case ADD_TO_GROUP:
                    Toast.makeText(v.getContext(), "Add To Group Trigger...", Toast.LENGTH_SHORT).show();
                    break;
                case SHARE_CHANNEL:
                    Toast.makeText(v.getContext(), "Share Channel Trigger...", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }


}
