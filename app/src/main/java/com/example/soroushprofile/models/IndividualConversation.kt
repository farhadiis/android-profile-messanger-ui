package com.example.soroushprofile.models


class IndividualConversation internal constructor(override val media: List<String>?,
                                                  val user: User) : ConversationThread() {

    override val title: String
        get() = user.name

    override val avatar: Int?
        get() = user.avatar

    override val type: ConversationType
        get() = ConversationType.Individual

    override val description: String?
        get() = user.bio
}
