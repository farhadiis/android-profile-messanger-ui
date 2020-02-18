package com.example.soroushprofile.models

abstract class ConversationThread {

    abstract val title: String

    abstract val avatar: Int?

    abstract val type: ConversationType

    abstract val media: List<String>?

    abstract val description: String?
}
