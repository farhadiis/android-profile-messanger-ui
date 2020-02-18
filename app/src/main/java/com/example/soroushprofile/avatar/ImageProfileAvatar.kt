package com.example.soroushprofile.avatar

import android.app.Activity
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.example.soroushprofile.models.ConversationThread

class ImageProfileAvatar internal constructor(activity: Activity, thread: ConversationThread,
                                              mHeaderImageView: ImageView,
                                              delegate: AvatarPaletteDelegate) : ProfileAvatar(activity, thread, mHeaderImageView, delegate) {

    override fun drawAvatar(imageView: ImageView) {
        Glide.with(activity).load(thread.avatar).into(imageView)
    }

}
