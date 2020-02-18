package com.example.soroushprofile.models

import com.example.soroushprofile.R
import java.util.*

object ConversationFactory {

    fun getThread(type: ConversationType): ConversationThread {
        when (type) {
            ConversationType.Individual -> {
                val bio = "27 y.o. designer from tehran, iran..., The Biotechnology Innovation Organization" +
                        " is the largest trade organization in the world that represents the biotechnology industry."
                val user = User("Farhad Azad", R.drawable.p01, bio)
                val media = object : ArrayList<String>() {
                    init {
                        add("1.jpg")
                        add("2.jpg")
                    }
                }
                return IndividualConversation(media, user)
            }
            ConversationType.Group -> return GroupConversation(null, null, "Soroush", 231)
            ConversationType.Channel -> return ChannelConversation(null, "khabar 24 is live..", "Khabar24", 429)
            else -> throw IllegalArgumentException("not supported type.")
        }
    }

}
