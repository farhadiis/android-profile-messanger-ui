package com.example.soroushprofile.avatar

import android.app.Activity
import android.graphics.Bitmap
import android.os.Build
import android.widget.ImageView
import androidx.palette.graphics.Palette

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.soroushprofile.R
import com.example.soroushprofile.models.ConversationThread

import jp.wasabeef.glide.transformations.BlurTransformation

abstract class ProfileAvatar internal constructor(internal val activity: Activity, val thread: ConversationThread,
                                                  private val mHeaderImageView: ImageView,
                                                  private val delegate: AvatarPaletteDelegate) : RequestListener<Bitmap> {

    abstract fun drawAvatar(imageView: ImageView)

    private fun drawHeader() {
        if (thread.avatar != null) {
            Glide.with(activity)
                    .asBitmap()
                    .load(thread.avatar)
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(10, 3)))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.colorPrimary)
                    .listener(this)
                    .into(mHeaderImageView)
        } else {
            delegate.onPalette(null)
        }
    }

    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap>, isFirstResource: Boolean): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startPostponedEnterTransition()
        }
        return false
    }

    override fun onResourceReady(resource: Bitmap?, model: Any, target: Target<Bitmap>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startPostponedEnterTransition()
        }
        if (resource != null) {
            Palette.from(resource).generate { delegate.onPalette(it) }
        }
        return false
    }

    companion object {


        fun of(activity: Activity, thread: ConversationThread,
               mAvatarImageView: ImageView, mHeaderImageView: ImageView,
               delegate: AvatarPaletteDelegate) {

            val profileAvatar: ProfileAvatar
            if (thread.avatar != null)
                profileAvatar = ImageProfileAvatar(activity, thread, mHeaderImageView, delegate)
            else
                profileAvatar = TextProfileAvatar(activity, thread, mHeaderImageView, delegate)

            profileAvatar.drawHeader()
            profileAvatar.drawAvatar(mAvatarImageView)
        }
    }
}
