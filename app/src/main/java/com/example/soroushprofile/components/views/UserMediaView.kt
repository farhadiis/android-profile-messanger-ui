package com.example.soroushprofile.components.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.example.soroushprofile.R
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.models.ConversationType
import com.example.soroushprofile.models.IndividualConversation
import com.example.soroushprofile.userprofile.UserMediaProfileAdapter
import kotlinx.android.synthetic.main.user_media_view.view.*

class UserMediaView : FrameLayout {

    private var glide: RequestManager? = null
    private var thread: ConversationThread? = null

    constructor(context: Context, thread: ConversationThread, glide: RequestManager) : super(context) {
        this.thread = thread
        this.glide = glide
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        inflate(context, R.layout.user_media_view, this)
        initializeList()
    }

    private fun initializeList() {
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycler_view.adapter = UserMediaProfileAdapter(null, glide!!)
        recycler_view.isNestedScrollingEnabled = false
    }

    private fun getAdapter(): UserMediaProfileAdapter = recycler_view.adapter as UserMediaProfileAdapter

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        load()
    }

    private fun load() {
        thread?.media?.let {
            if (it.isEmpty()) setPlaceholder() else {
                setMedia(it)
            }
        } ?: run {
            setPlaceholder()
        }
    }

    private fun setMedia(media: List<String>) {
        recycler_view.visibility = View.VISIBLE
        no_content.visibility = View.GONE
        getAdapter().list = media
    }

    private fun setPlaceholder() {
        no_content.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
        no_content.text = when (thread?.type) {
            ConversationType.Individual -> {
                val conversation = thread as IndividualConversation
                context.getString(R.string.profile_user_media_no_media_individual,
                        conversation.user.name)
            }
            ConversationType.Group -> context.getString(R.string.profile_user_media_no_media_group)
            ConversationType.Channel -> context.getString(R.string.profile_user_media_no_media_channel)
            else -> throw IllegalArgumentException("invalid args")
        }
    }
}
