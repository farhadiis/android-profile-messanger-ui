package com.example.soroushprofile.userprofile.avatar

import android.app.Activity
import android.graphics.Bitmap
import android.os.Build
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.soroushprofile.R
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.util.ColorUtil
import com.google.android.material.appbar.CollapsingToolbarLayout
import jp.wasabeef.glide.transformations.BlurTransformation

abstract class ProfileAvatar internal constructor(internal val activity: Activity,
                                                  protected val thread: ConversationThread,
                                                  private val mHeaderImageView: ImageView,
                                                  protected val glide: RequestManager,
                                                  private val toolbarLayout: CollapsingToolbarLayout) : RequestListener<Bitmap> {

    abstract fun drawAvatar(imageView: ImageView)

    private fun drawHeader() {
        if (thread.avatar != null) {
            glide.asBitmap()
                    .load(thread.avatar)
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(10, 3)))
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.colorPrimary)
                    .listener(this)
                    .into(mHeaderImageView)
        } else {
            initializeColorPalette(null)
        }
    }

    private fun initializeColorPalette(palette: Palette?) {
        val colorPrimary = ContextCompat.getColor(activity, R.color.colorPrimary)
        val colorPrimaryDark = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
        palette?.let {
            val vibrantColor = it.getMutedColor(colorPrimary)
            toolbarLayout.setContentScrimColor(vibrantColor)
            val darkVibrantColor = ColorUtil.manipulateColor(vibrantColor, 0.8f)
            toolbarLayout.setStatusBarScrimColor(darkVibrantColor)
        } ?: run {
            toolbarLayout.setContentScrimColor(colorPrimary)
            toolbarLayout.setStatusBarScrimColor(colorPrimaryDark)
        }
    }

    override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap>,
                              isFirstResource: Boolean): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startPostponedEnterTransition()
        }
        return false
    }

    override fun onResourceReady(resource: Bitmap?, model: Any, target: Target<Bitmap>,
                                 dataSource: DataSource, isFirstResource: Boolean): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.startPostponedEnterTransition()
        }
        resource?.let {
            Palette.from(it).generate { initializeColorPalette(it) }
        } ?: run {
            initializeColorPalette(null)
        }
        return false
    }

    companion object {

        fun show(activity: Activity, thread: ConversationThread,
                 mAvatarImageView: ImageView, mHeaderImageView: ImageView, glide: RequestManager,
                 mToolbarLayout: CollapsingToolbarLayout) {

            val profileAvatar = if (thread.avatar != null)
                ImageProfileAvatar(activity, thread, mHeaderImageView, glide, mToolbarLayout)
            else TextProfileAvatar(activity, thread, mHeaderImageView, glide, mToolbarLayout)

            profileAvatar.drawHeader()
            profileAvatar.drawAvatar(mAvatarImageView)
        }
    }
}
