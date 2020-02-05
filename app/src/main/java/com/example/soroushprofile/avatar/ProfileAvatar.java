package com.example.soroushprofile.avatar;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.soroushprofile.R;
import com.example.soroushprofile.models.User;

import jp.wasabeef.glide.transformations.BlurTransformation;

public abstract class ProfileAvatar implements RequestListener<Bitmap> {

    @NonNull
    private Activity activity;

    @NonNull
    private User user;

    @NonNull
    private ImageView mHeaderImageView;

    @NonNull
    private AvatarPaletteDelegate delegate;

    ProfileAvatar(@NonNull Activity activity, @NonNull User user,
                         @NonNull ImageView mHeaderImageView,
                         @NonNull AvatarPaletteDelegate delegate) {
        this.activity = activity;
        this.user = user;
        this.mHeaderImageView = mHeaderImageView;
        this.delegate = delegate;
    }


    public static void of(@NonNull Activity activity, @NonNull User user,
                          @NonNull ImageView mAvatarImageView, @NonNull ImageView mHeaderImageView,
                          @NonNull AvatarPaletteDelegate delegate) {

        ProfileAvatar profileAvatar;
        if (user.getAvatar() != null)
            profileAvatar = new ImageProfileAvatar(activity, user, mHeaderImageView, delegate);
        else
            profileAvatar = new TextProfileAvatar(activity, user, mHeaderImageView, delegate);

        profileAvatar.drawHeader();
        profileAvatar.drawAvatar(mAvatarImageView);
    }

    public abstract void drawAvatar(ImageView imageView);

    private void drawHeader() {
        if (user.getAvatar() != null) {
            Glide.with(activity)
                    .asBitmap()
                    .load(user.getAvatar())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(10, 3)))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.colorPrimary)
                    .listener(this)
                    .into(mHeaderImageView);
        } else {
            delegate.onPalette(null);
        }
    }

    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startPostponedEnterTransition();
        }
        return false;
    }

    @Override
    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startPostponedEnterTransition();
        }
        if (resource != null) {
            Palette.from(resource).generate(delegate::onPalette);
        }
        return false;
    }

    @NonNull
    Activity getActivity() {
        return activity;
    }


    @NonNull
    User getUser() {
        return user;
    }

}
