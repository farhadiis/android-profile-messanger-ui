package com.example.soroushprofile.userprofile.avatar

import android.app.Activity
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.example.soroushprofile.models.ConversationThread
import com.google.android.material.appbar.CollapsingToolbarLayout

class ImageProfileAvatar internal constructor(activity: Activity,
                                              thread: ConversationThread,
                                              mHeaderImageView: ImageView,
                                              mToolbarLayout: CollapsingToolbarLayout) : ProfileAvatar(activity, thread, mHeaderImageView, mToolbarLayout) {

    override fun drawAvatar(imageView: ImageView) {
        Glide.with(activity).load(thread.avatar).into(imageView)
    }

}
