package com.example.soroushprofile.userprofile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soroushprofile.models.ConversationFactory;
import com.example.soroushprofile.models.ConversationThread;
import com.example.soroushprofile.models.ConversationType;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<ConversationThread> mConversationThread;

    public LiveData<ConversationThread> getConversationThread(String type) {
        if (mConversationThread == null) {
            mConversationThread = new MutableLiveData<>();
           loadThread(type);
        }
        return mConversationThread;
    }

    private void loadThread(String type) {
        ConversationType key = ConversationType.valueOf(type);
        mConversationThread.setValue(ConversationFactory.getThread(key));
    }

}
