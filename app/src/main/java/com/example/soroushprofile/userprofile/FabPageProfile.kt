package com.example.soroushprofile.userprofile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.soroushprofile.R
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.models.ConversationType
import com.example.soroushprofile.util.ViewUtil
import com.example.soroushprofile.util.fabutil.FabClickListener
import com.example.soroushprofile.util.fabutil.FabInjector
import com.example.soroushprofile.util.fabutil.FabOption
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FabPageProfile constructor(private val thread: ConversationThread,
                                 private val inflater: LayoutInflater,
                                 private val root: ViewGroup) : FabInjector {

    @SuppressLint("InflateParams")
    override fun onInject(listener: FabClickListener) {

        when (thread.type) {
            ConversationType.Individual -> {
                val fabVoiceCall = inflater
                        .inflate(R.layout.profile_fab_item, null) as FloatingActionButton
                val fabVideoCall = inflater
                        .inflate(R.layout.profile_fab_item, null) as FloatingActionButton

                fabVideoCall.setOnClickListener { listener.onFabClick(FabOption.VIDEO_CALL) }
                fabVoiceCall.setOnClickListener { listener.onFabClick(FabOption.VOICE_CALL) }

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

                fabShare.setOnClickListener { listener.onFabClick(FabOption.SHARE_CHANNEL) }
                fabShare.setImageResource(R.drawable.ic_share_white_24dp)

                root.addView(fabShare,
                        ViewUtil.getCoordinatorLayoutParams(root.context, R.id.app_bar, 16))
            }

            ConversationType.Group -> {

                val fabAdd = inflater
                        .inflate(R.layout.profile_fab_item, null) as FloatingActionButton

                fabAdd.setOnClickListener { listener.onFabClick(FabOption.ADD_TO_GROUP) }
                fabAdd.setImageResource(R.drawable.ic_group_add_white_24dp)

                root.addView(fabAdd,
                        ViewUtil.getCoordinatorLayoutParams(root.context, R.id.app_bar, 16))
            }
        }
    }

}