package com.example.soroushprofile.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IndividualConversation extends ConversationThread {

    private User user;

    public IndividualConversation(User user) {
        this.user = user;
    }

    @NonNull
    @Override
    public String getTitle() {
        return user.getName();
    }

    @Nullable
    @Override
    public Integer getAvatar() {
        return user.getAvatar();
    }

    public User getUser() {
        return user;
    }
}
