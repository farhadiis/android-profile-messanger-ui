package com.example.soroushprofile.util.fabutil

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.soroushprofile.R
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.models.ConversationType
import com.example.soroushprofile.util.ViewUtil
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabInjectProcessor constructor(private val thread: ConversationThread,
                                     private val inflater: LayoutInflater,
                                     private val root: ViewGroup) : FabInjector {

    var mFabListener: FabClickListener? = null

    @SuppressLint("InflateParams")
    override fun onInject() {

        when (thread.type) {
            ConversationType.Individual -> {
                val fabVoiceCall = inflater
                        .inflate(R.layout.profile_fab_item, null) as FloatingActionButton
                val fabVideoCall = inflater
                        .inflate(R.layout.profile_fab_item, null) as FloatingActionButton

                fabVideoCall.setOnClickListener { mFabListener?.onFabClick(FabOption.VIDEO_CALL) }
                fabVoiceCall.setOnClickListener { mFabListener?.onFabClick(FabOption.VOICE_CALL) }

                fabVideoCall.setImageResource(R.drawable.ic_video_call_white_24dp)
                fabVoiceCall.setImageResource(R.drawable.ic_call_white_24dp)

                root.addView(fabVideoCall,
                        ViewUtil.getCoordinatorLayoutParams(root.context, R.id.app_bar, 16))

                root.addView(fabVoiceCall,
                        ViewUtil.getCoordinatorLayoutParams(root.context, R.id.app_bar, 64))
            }

            ConversationType.Channel -> {
                val fabShare = inflater
                        .inflate(R.layout.profile_fab_item, null) as FloatingActionButton

                fabShare.setOnClickListener { mFabListener?.onFabClick(FabOption.SHARE_CHANNEL) }
                fabShare.setImageResource(R.drawable.ic_share_white_24dp)

                root.addView(fabShare,
                        ViewUtil.getCoordinatorLayoutParams(root.context, R.id.app_bar, 16))
            }

            ConversationType.Group -> {

                val fabAdd = inflater
                        .inflate(R.layout.profile_fab_item, null) as FloatingActionButton

                fabAdd.setOnClickListener { mFabListener?.onFabClick(FabOption.ADD_TO_GROUP) }
                fabAdd.setImageResource(R.drawable.ic_group_add_white_24dp)

                root.addView(fabAdd,
                        ViewUtil.getCoordinatorLayoutParams(root.context, R.id.app_bar, 16))
            }
        }
    }

}