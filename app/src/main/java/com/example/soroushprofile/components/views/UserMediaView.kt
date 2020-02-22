package com.example.soroushprofile.components.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soroushprofile.R
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.models.ConversationType
import com.example.soroushprofile.models.IndividualConversation
import com.example.soroushprofile.userprofile.UserMediaProfileAdapter
import kotlinx.android.synthetic.main.user_media_view.view.*

class UserMediaView : FrameLayout {

    private var thread: ConversationThread? = null

    constructor(context: Context, thread: ConversationThread) : super(context) {
        this.thread = thread
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
        initializeRes()
    }


    private fun initializeRes() {

        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = UserMediaProfileAdapter(null)
        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = layoutManager
        recycler_view.adapter = adapter
        recycler_view.isNestedScrollingEnabled = false
        refresh()
    }


    private fun refresh() {
        val a = thread?.media?.let {
            setPlaceholder()
        } ?: run {
            setMedia()
        }
    }

    private fun setMedia() {
        recycler_view.visibility = View.VISIBLE
        no_content.visibility = View.GONE
        right_summary.visibility = View.VISIBLE
        title.visibility = View.VISIBLE
    }

    private fun setPlaceholder() {
        no_content.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
        right_summary.visibility = View.GONE
        title.visibility = View.GONE
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
