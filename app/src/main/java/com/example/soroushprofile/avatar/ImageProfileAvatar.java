package com.example.soroushprofile.avatar;

import android.app.Activity;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.soroushprofile.models.User;

public class ImageProfileAvatar extends ProfileAvatar {

    ImageProfileAvatar(@NonNull Activity activity, @NonNull User user,
                       @NonNull ImageView mHeaderImageView,
                       @NonNull AvatarPaletteDelegate delegate) {
        super(activity, user, mHeaderImageView, delegate);
    }

    @Override
    public void drawAvatar(ImageView imageView) {
        Glide.with(getActivity()).load(getUser().getAvatar()).into(imageView);
    }

}
