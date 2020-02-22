package com.example.soroushprofile.userprofile

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import com.bumptech.glide.RequestManager
import com.example.soroushprofile.R
import com.example.soroushprofile.components.views.ProfileItemView
import com.example.soroushprofile.components.views.UserMediaView
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.models.ConversationType
import com.example.soroushprofile.util.PageConstructor

class ProfilePage constructor(private val thread: ConversationThread,
                              private val inflater: LayoutInflater,
                              private val glide: RequestManager,
                              private val body: ViewGroup) : PageConstructor {

    private val divider: View
        get() = inflater.inflate(R.layout.profile_divider, null)

    @SuppressLint("InflateParams")
    override fun onCreate() {

        val context = body.context

        val id = ProfileItemView(context)
        id.titleText = context.getText(R.string.profile_user_username)
        id.summaryText = "@farhad_azad"
        body.addView(id)

        when (thread.type) {
            ConversationType.Individual -> {
                val bio = ProfileItemView(context)
                bio.titleText = context.getString(R.string.profile_user_description_bio_title)
                bio.summaryText = thread.description
                        ?: context.getString(R.string.profile_user_description_bio_empty)
                body.addView(bio)
            }
            ConversationType.Channel -> {
                val bio = ProfileItemView(context)
                bio.titleText = context.getString(R.string.profile_user_description_title)
                bio.summaryText = thread.description
                        ?: context.getString(R.string.profile_user_description_empty)
                body.addView(bio)
            }
            ConversationType.Group -> {
                val bio = ProfileItemView(context)
                bio.titleText = context.getString(R.string.profile_user_description_title)
                bio.summaryText = thread.description
                        ?: context.getString(R.string.profile_user_description_empty)
                body.addView(bio)
            }
        }

        body.addView(divider)

        val userMediaView = UserMediaView(context, thread, glide)
        body.addView(userMediaView)

        body.addView(divider)

        val notification = ProfileItemView(context)
        notification.summaryText = context.getString(R.string.profile_user_notification)
        notification.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            Toast.makeText(context, "checked to $isChecked", Toast.LENGTH_SHORT).show()
        })
        body.addView(notification)

        val clearConversation = ProfileItemView(context)
        clearConversation.summaryText = context.getString(R.string.profile_user_clear_conversation)
        body.addView(clearConversation)

        val restrict = ProfileItemView(context)
        restrict.summaryText = when (thread.type) {
            ConversationType.Individual -> context.getString(R.string.profile_user_restrict_individual)
            ConversationType.Channel -> context.getString(R.string.profile_user_restrict_channel)
            ConversationType.Group -> context.getString(R.string.profile_user_restrict_group)
        }
        restrict.summaryTextColor = Color.RED
        body.addView(restrict)

    }
}