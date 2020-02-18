package com.example.soroushprofile.userprofile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.soroushprofile.models.ConversationFactory
import com.example.soroushprofile.models.ConversationThread
import com.example.soroushprofile.models.ConversationType

class ProfileViewModel : ViewModel() {

    private val mConversationThread: MutableLiveData<ConversationThread> by lazy {
        MutableLiveData<ConversationThread>()
    }

    fun getConversationThread(type: String): MutableLiveData<ConversationThread> {
        val key = ConversationType.valueOf(type)
        mConversationThread.value = ConversationFactory.getThread(key)
        return mConversationThread
    }

}
