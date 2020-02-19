package com.example.soroushprofile.components.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.soroushprofile.models.ConversationThread

class UserMediaView : View {

    private var thread: ConversationThread? = null

    private var mRecyclerView: RecyclerView? = null
    private var mNoContentTextView: TextView? = null


    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {

    }


//    override fun onBindViewHolder(holder: PreferenceViewHolder) {
//        super.onBindViewHolder(holder)
//
//        mRecyclerView = holder.itemView.findViewById(R.id.recycler_view)
//        mNoContentTextView = holder.itemView.findViewById(R.id.no_content)
//
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        val adapter = UserMediaProfileAdapter(null)
//        mRecyclerView!!.setHasFixedSize(true)
//        mRecyclerView!!.layoutManager = layoutManager
//        mRecyclerView!!.adapter = adapter
//        mRecyclerView!!.isNestedScrollingEnabled = false
//
//
//        refresh(thread!!)
//    }
//
//    fun refresh(thread: ConversationThread) {
//        this.thread = thread
//        if (thread.media!!.isEmpty()) {
//            if (mNoContentTextView == null) return
//            mNoContentTextView!!.visibility = View.VISIBLE
//            mRecyclerView!!.visibility = View.GONE
//            setPlaceholder(thread)
//        } else {
//            if (mRecyclerView == null) return
//            mRecyclerView!!.visibility = View.VISIBLE
//            mNoContentTextView!!.visibility = View.GONE
//            setMedia(thread)
//        }
//    }
//
//    private fun setMedia(thread: ConversationThread) {}
//
//    private fun setPlaceholder(thread: ConversationThread) {
//        val placeholder: String
//        when (thread.type) {
//            ConversationType.Individual -> {
//                val conversation = thread as IndividualConversation
//                placeholder = context.getString(R.string.profile_user_media_no_media_individual,
//                        conversation.user.name)
//            }
//            ConversationType.Group -> placeholder = context.getString(R.string.profile_user_media_no_media_group)
//            ConversationType.Channel -> placeholder = context.getString(R.string.profile_user_media_no_media_channel)
//            else -> throw IllegalArgumentException("invalid argument")
//        }
//        mNoContentTextView!!.text = placeholder
//    }
}