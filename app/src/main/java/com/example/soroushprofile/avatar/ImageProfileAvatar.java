package com.example.soroushprofile.avatar;

import android.app.Activity;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.soroushprofile.models.ConversationThread;

public class ImageProfileAvatar extends ProfileAvatar {

    ImageProfileAvatar(@NonNull Activity activity, @NonNull ConversationThread thread,
                       @NonNull ImageView mHeaderImageView,
                       @NonNull AvatarPaletteDelegate delegate) {
        super(activity, thread, mHeaderImageView, delegate);
    }

    @Override
    public void drawAvatar(ImageView imageView) {
        Glide.with(getActivity()).load(getThread().getAvatar()).into(imageView);
    }

}
