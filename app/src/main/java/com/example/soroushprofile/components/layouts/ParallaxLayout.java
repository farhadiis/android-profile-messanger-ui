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

    private ImageView       mAvatarImageView;
    private TextView        mUsernameTextView;
    private View            mPinView;

    private AppBarStateChangeListener mAppBarStateChangeListener;

    private float EXPAND_AVATAR_SIZE_DP;
    private float COLLAPSED_AVATAR_SIZE_DP;

    private float[] mAvatarPoint            = new float[2];
    private float[] mPinPoint               = new float[2];
    private float[] mUsernameTextViewPoint  = new float[2];

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
    }

    private void initializeRes() {
        mAvatarImageView            = findViewById(R.id.avatar_image_view);
        mPinView                    = findViewById(R.id.pin_view);
        mUsernameTextView           = findViewById(R.id.username);

        AppBarLayout mAppBarLayout = findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(mAppBarStateChangeListener);
    }

    private void init() {
        EXPAND_AVATAR_SIZE_DP       = Util.convertPixelsToDp(getResources().getDimension(R.dimen.avatar_size), getContext());
        COLLAPSED_AVATAR_SIZE_DP    = Util.convertPixelsToDp(getResources().getDimension(R.dimen.pin_avatar_size), getContext());
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
        float xAvatarOffset = (mPinPoint[0] - mAvatarPoint[0]) * offset;
        float yAvatarOffset = (mPinPoint[1] - mAvatarPoint[1] - (expandAvatarSize - newAvatarSize)) * offset;

        mAvatarImageView.setTranslationX(xAvatarOffset);
        mAvatarImageView.setTranslationY(yAvatarOffset);
        mAvatarImageView.getLayoutParams().width = newAvatarSize;
        mAvatarImageView.getLayoutParams().height = newAvatarSize;
        mAvatarImageView.requestLayout();

        float xTitleOffset = (mPinPoint[0] - mUsernameTextViewPoint[0] + 150) * offset;
        float yTitleOffset = (mPinPoint[1] - mUsernameTextViewPoint[1] - 10) * offset;
        mUsernameTextView.setTranslationX(xTitleOffset);
        mUsernameTextView.setTranslationY(yTitleOffset);
    }

    private void calcPoints() {

        final float offset = mAppBarStateChangeListener.getCurrentOffset();

        float newAvatarSize = Util.convertDpToPixel(EXPAND_AVATAR_SIZE_DP - (EXPAND_AVATAR_SIZE_DP - COLLAPSED_AVATAR_SIZE_DP) * offset, getContext());
        float expandAvatarSize = Util.convertDpToPixel(EXPAND_AVATAR_SIZE_DP, getContext());

        int[] avatarPoint = new int[2];
        mAvatarImageView.getLocationOnScreen(avatarPoint);
        mAvatarPoint[0] = avatarPoint[0] - mAvatarImageView.getTranslationX();
        mAvatarPoint[1] = avatarPoint[1] - mAvatarImageView.getTranslationY() - (expandAvatarSize - newAvatarSize);

        int[] pinPoint = new int[2];
        mPinView.getLocationOnScreen(pinPoint);
        mPinPoint[0] = pinPoint[0];
        mPinPoint[1] = pinPoint[1];

        int[] usernameTextViewPoint = new int[2];
        mUsernameTextView.getLocationOnScreen(usernameTextViewPoint);
        mUsernameTextViewPoint[0] = usernameTextViewPoint[0] - mUsernameTextView.getTranslationX();
        mUsernameTextViewPoint[1] = usernameTextViewPoint[1] - mUsernameTextView.getTranslationY();
    }


}
