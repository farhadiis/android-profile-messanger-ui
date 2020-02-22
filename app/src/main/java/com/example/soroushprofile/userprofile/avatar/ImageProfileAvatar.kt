package com.example.soroushprofile.userprofile.avatar

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.example.soroushprofile.models.ConversationThread
import com.google.android.material.appbar.CollapsingToolbarLayout

class ImageProfileAvatar internal constructor(activity: Activity,
                                              thread: ConversationThread,
                                              mHeaderImageView: ImageView,
                                              glide: RequestManager,
                                              mToolbarLayout: CollapsingToolbarLayout) : ProfileAvatar(activity, thread, mHeaderImageView, glide, mToolbarLayout) {

    override fun drawAvatar(imageView: ImageView) {
        glide.load(thread.avatar).into(imageView)
    }

}
