package com.example.soroushprofile.userprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.soroushprofile.models.ConversationFactory
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.models.ConversationType
import java.lang.IllegalArgumentException

class ProfileViewModel : ViewModel() {

    val mConversationThread: MutableLiveData<ConversationThread> by lazy {
        MutableLiveData<ConversationThread>()
    }

    fun initialize(type: ConversationType) {
        mConversationThread.value = ConversationFactory.getThread(type)
    }

}
