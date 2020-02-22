package com.example.soroushprofile.userprofile.avatar

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.RequestManager
import com.example.soroushprofile.R
import com.example.soroushprofile.models.ConversationThread
import com.google.android.material.appbar.CollapsingToolbarLayout

class TextProfileAvatar internal constructor(activity: Activity,
                                             thread: ConversationThread,
                                             mHeaderImageView: ImageView,
                                             glide: RequestManager,
                                             mToolbarLayout: CollapsingToolbarLayout) : ProfileAvatar(activity, thread, mHeaderImageView, glide, mToolbarLayout) {

    override fun drawAvatar(imageView: ImageView) {
        val title = thread.title
        if (!title.isEmpty()) {
            val text = title.substring(0, 1)
            val colorPrimaryDark = ContextCompat.getColor(activity, R.color.colorPrimaryDark)
            val resources = activity.resources
            val size = resources.getDimension(R.dimen.avatar_size).toInt()
            val bitmap = generateTextThumbnail(text, colorPrimaryDark, size)
            imageView.setImageBitmap(bitmap)
        }
    }

    private fun generateTextThumbnail(text: String, color: Int, size: Int): Bitmap {
        val paint = Paint(ANTI_ALIAS_FLAG)
        paint.textSize = size.toFloat()
        paint.color = Color.WHITE
        paint.textAlign = Paint.Align.LEFT
        val baseline = -paint.ascent() // ascent() is negative
        var width = (paint.measureText(text) + 0.0f).toInt() // round
        var height = (baseline + paint.descent() + 0.0f).toInt()

        val padding = size / 2
        val trueWidth = width
        if (width > height)
            height = width
        else
            width = height
        val image = Bitmap.createBitmap(width + padding, height + padding, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(image)
        canvas.drawColor(color)
        canvas.drawText(text, (width / 2 - trueWidth / 2 + padding / 2).toFloat(), baseline + padding / 2f, paint)
        return image
    }

}
