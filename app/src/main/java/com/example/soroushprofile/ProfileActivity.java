package com.example.soroushprofile;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.example.soroushprofile.avatar.ProfileAvatar;
import com.example.soroushprofile.models.User;
import com.example.soroushprofile.util.ColorUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;


public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = ProfileActivity.class.getSimpleName();

    private User user;

    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView mHeaderImageView;
    private ImageView mAvatarImageView;
    private TextView mUsernameTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializeRes();
        initializeToolbar();

        this.user = new User("Farhad Azad", R.drawable.p01);

        initializeProfileAvatar();
        initializeUserDate();
    }

    private void initializeUserDate() {
        mUsernameTextView.setText(user.getName());
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_video_call:
                Toast.makeText(this, "Video Call Trigger...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_voice_call:
                Toast.makeText(this, "Voice Call Trigger...", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initializeRes() {
        mCollapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        mHeaderImageView = findViewById(R.id.header_image_view);
        mAvatarImageView = findViewById(R.id.avatar_image_view);
        mUsernameTextView = findViewById(R.id.username);


        findViewById(R.id.fab_video_call).setOnClickListener(this);
        findViewById(R.id.fab_voice_call).setOnClickListener(this);
    }

    private void initializeToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void initializeProfileAvatar() {
        ProfileAvatar.of(this, user, mAvatarImageView, mHeaderImageView, palette -> {
            int colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
            int colorPrimaryDark = ContextCompat.getColor(this, R.color.colorPrimaryDark);
            if (palette != null) {
                int vibrantColor = palette.getDominantColor(colorPrimary);
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
        });
    }

}
