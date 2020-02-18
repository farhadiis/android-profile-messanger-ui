package com.example.soroushprofile.models

class ChannelConversation internal constructor(override val media: List<String>?,
                                               override val description: String?,
                                               override val title: String,
                                               val subscriber: Int?) : ConversationThread() {

    override val avatar: Int?
        get() = null

    override val type: ConversationType
        get() = ConversationType.Channel
}
