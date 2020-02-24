package com.example.soroushprofile.models

import com.example.soroushprofile.R

object ConversationFactory {

    fun getThread(type: ConversationType): ConversationThread {
        when (type) {
            ConversationType.Individual -> {
                val bio = "27 y.o. designer from tehran, iran..., The Biotechnology Innovation Organization" +
                        " is the largest trade organization in the world that represents the biotechnology industry."
                val user = User("Farhad Azad", R.drawable.header, bio)
                val prefix = "https://baelm.net/wp-content/uploads/2014/11"
                val media = arrayListOf(
                        "$prefix/Nature-Photo-2-634x467.jpg",
                        "$prefix/Nature-Photo-3.jpg",
                        "$prefix/Nature-Photo-5-634x475.jpg",
                        "$prefix/Nature-Photo-101-634x475.jpg",
                        "$prefix/Nature-Photo-14-634x396.jpg",
                        "$prefix/Nature-Photo-16-634x410.jpg",
                        "$prefix/Nature-Photo-19-634x396.jpg",
                        "$prefix/Nature-Photo-2-634x467.jpg",
                        "$prefix/Nature-Photo-3.jpg",
                        "$prefix/Nature-Photo-5-634x475.jpg",
                        "$prefix/Nature-Photo-101-634x475.jpg",
                        "$prefix/Nature-Photo-14-634x396.jpg",
                        "$prefix/Nature-Photo-16-634x410.jpg",
                        "$prefix/Nature-Photo-19-634x396.jpg"
                )
                return IndividualConversation(media, user)
            }

            ConversationType.Group -> return GroupConversation(null, null, "Soroush", 231)
            ConversationType.Channel -> return ChannelConversation(null, "khabar 24 is live..", "Khabar24", 429)
        }
    }

}
