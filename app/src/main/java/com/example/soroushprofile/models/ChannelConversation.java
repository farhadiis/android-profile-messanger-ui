package com.example.soroushprofile.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChannelConversation extends ConversationThread {

    private String name;
    private Integer subscriber;

    public ChannelConversation(String name, Integer subscriber) {
        this.name = name;
        this.subscriber = subscriber;
    }

    @NonNull
    @Override
    public String getTitle() {
        return name;
    }

    @Nullable
    @Override
    public Integer getAvatar() {
        return null;
    }

    public Integer getSubscriber() {
        return subscriber;
    }
}
