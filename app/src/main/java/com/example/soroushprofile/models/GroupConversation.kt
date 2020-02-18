package com.example.soroushprofile.models

class GroupConversation internal constructor(override val media: List<String>?,
                                             override val description: String?,
                                             override val title: String,
                                             val member: Int?) : ConversationThread() {

    override val avatar: Int?
        get() = null

    override val type: ConversationType
        get() = ConversationType.Group
}
