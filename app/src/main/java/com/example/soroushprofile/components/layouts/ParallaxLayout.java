package com.example.soroushprofile.components.layouts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.example.soroushprofile.R;
import com.example.soroushprofile.util.Util;
import com.google.android.material.appbar.AppBarLayout;

public class ParallaxLayout extends CoordinatorLayout {

    private ImageView mAvatarImageView;
    private View mPinAvatarView;

    private TextView mUsernameTextView;
    private TextView mPinUsernameTextView;

    private TextView mStatusTextView;
    private TextView mPinStatusTextView;

    private AppBarStateChangeListener mAppBarStateChangeListener;

    private float EXPAND_AVATAR_SIZE_DP;
    private float COLLAPSED_AVATAR_SIZE_DP;

    private float[] mAvatarPoint = new float[2];
    private float[] mAvatarPinPoint = new float[2];
    private float[] mUsernamePoint = new float[2];
    private float[] mUsernamePinPoint = new float[2];
    private float[] mStatusPoint = new float[2];
    private float[] mStatusPinPoint = new float[2];

    public ParallaxLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public ParallaxLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParallaxLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initializeRes();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        calcPoints();
        translationView(mAppBarStateChangeListener.getCurrentOffset());
    }

    private void initializeRes() {
        mAvatarImageView = findViewById(R.id.avatar_image_view);
        mPinAvatarView = findViewById(R.id.pin_avatar_view);
        mUsernameTextView = findViewById(R.id.username);
        mPinUsernameTextView = findViewById(R.id.pin_username);
        mStatusTextView = findViewById(R.id.status);
        mPinStatusTextView = findViewById(R.id.pin_status);

        AppBarLayout mAppBarLayout = findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(mAppBarStateChangeListener);
    }

    private void init() {
        EXPAND_AVATAR_SIZE_DP = Util.convertPixelsToDp(getResources().getDimension(R.dimen.avatar_size), getContext());
        COLLAPSED_AVATAR_SIZE_DP = Util.convertPixelsToDp(getResources().getDimension(R.dimen.pin_avatar_size), getContext());
        mAppBarStateChangeListener = new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout,
                                       AppBarStateChangeListener.State state) {
            }

            @Override
            public void onOffsetChanged(AppBarStateChangeListener.State state, float offset) {
                translationView(offset);
            }
        };
    }

    private void translationView(float offset) {

        int newAvatarSize = (int) Util.convertDpToPixel(EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset, getContext());

        float expandAvatarSize = Util.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, getContext());
        float scaleAvatar = expandAvatarSize - newAvatarSize;
        float xAvatarOffset = (mAvatarPinPoint[0] - mAvatarPoint[0]) * offset;
        float yAvatarOffset = (mAvatarPinPoint[1] - mAvatarPoint[1] - scaleAvatar) * offset;

        mAvatarImageView.setTranslationX(xAvatarOffset);
        mAvatarImageView.setTranslationY(yAvatarOffset);
        mAvatarImageView.getLayoutParams().width = newAvatarSize;
        mAvatarImageView.getLayoutParams().height = newAvatarSize;
        mAvatarImageView.requestLayout();

        float xTitleOffset = (mUsernamePinPoint[0] - mUsernamePoint[0] + scaleAvatar) * offset;
        float yTitleOffset = (mUsernamePinPoint[1] - mUsernamePoint[1]) * offset;
        mUsernameTextView.setTranslationX(xTitleOffset);
        mUsernameTextView.setTranslationY(yTitleOffset);


        float xStatusOffset = (mStatusPinPoint[0] - mStatusPoint[0] + scaleAvatar) * offset;
        float yStatusOffset = (mStatusPinPoint[1] - mStatusPoint[1]) * offset;
        mStatusTextView.setTranslationX(xStatusOffset);
        mStatusTextView.setTranslationY(yStatusOffset);
    }

    private void calcPoints() {

        final float offset = mAppBarStateChangeListener.getCurrentOffset();

        float newAvatarSize = Util.convertDpToPixel(EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset, getContext());
        float expandAvatarSize = Util.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, getContext());
        float scaleAvatar = expandAvatarSize - newAvatarSize;

        int[] avatarPoint = new int[2];
        mAvatarImageView.getLocationOnScreen(avatarPoint);
        mAvatarPoint[0] = avatarPoint[0] - mAvatarImageView.getTranslationX();
        mAvatarPoint[1] = avatarPoint[1] - mAvatarImageView.getTranslationY() - scaleAvatar;

        int[] pinAvatarPoint = new int[2];
        mPinAvatarView.getLocationOnScreen(pinAvatarPoint);
        mAvatarPinPoint[0] = pinAvatarPoint[0];
        mAvatarPinPoint[1] = pinAvatarPoint[1];

        int[] usernamePoint = new int[2];
        mUsernameTextView.getLocationOnScreen(usernamePoint);
        mUsernamePoint[0] = usernamePoint[0] - mUsernameTextView.getTranslationX() + scaleAvatar;
        mUsernamePoint[1] = usernamePoint[1] - mUsernameTextView.getTranslationY();

        int[] usernamePinPoint = new int[2];
        mPinUsernameTextView.getLocationOnScreen(usernamePinPoint);
        mUsernamePinPoint[0] = usernamePinPoint[0] - mPinUsernameTextView.getTranslationX();
        mUsernamePinPoint[1] = usernamePinPoint[1] - mPinUsernameTextView.getTranslationY();


        int[] statusPoint = new int[2];
        mStatusTextView.getLocationOnScreen(statusPoint);
        mStatusPoint[0] = statusPoint[0] - mStatusTextView.getTranslationX() + scaleAvatar;
        mStatusPoint[1] = statusPoint[1] - mStatusTextView.getTranslationY();

        int[] statusPinPoint = new int[2];
        mPinStatusTextView.getLocationOnScreen(statusPinPoint);
        mStatusPinPoint[0] = statusPinPoint[0] - mPinStatusTextView.getTranslationX();
        mStatusPinPoint[1] = statusPinPoint[1] - mPinStatusTextView.getTranslationY();
    }


}
