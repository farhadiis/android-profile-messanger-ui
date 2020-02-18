package com.example.soroushprofile.models

enum class ConversationType {

    Individual, Group, Channel;

    val isIndividual: Boolean
        get() = this == Individual
}
