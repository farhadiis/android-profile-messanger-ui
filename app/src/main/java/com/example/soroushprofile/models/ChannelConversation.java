package com.example.soroushprofile.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.annimon.stream.Optional;

import java.util.List;

public class ChannelConversation extends ConversationThread {

    private String name;
    private Integer subscriber;
    private Optional<List<String>> media;
    private Optional<String> description;

    ChannelConversation(Optional<List<String>> media, Optional<String> description, String name, Integer subscriber) {
        this.name = name;
        this.subscriber = subscriber;
        this.media = media;
        this.description = description;
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

    @NonNull
    @Override
    public ConversationType getType() {
        return ConversationType.channel;
    }

    @Override
    public Optional<List<String>> getMedia() {
        return media;
    }

    @Override
    public Optional<String> getDescription() {
        return description;
    }

    public Integer getSubscriber() {
        return subscriber;
    }
}
