package com.example.soroushprofile.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.annimon.stream.Optional;

import java.util.List;

public class IndividualConversation extends ConversationThread {

    private User user;
    private Optional<List<String>> media;

    IndividualConversation(Optional<List<String>> media, User user) {
        this.user = user;
        this.media = media;
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

    @NonNull
    @Override
    public ConversationType getType() {
        return ConversationType.individual;
    }

    @Override
    public Optional<List<String>> getMedia() {
        return media;
    }

    @Override
    public Optional<String> getDescription() {
        return user.getBio();
    }

    public User getUser() {
        return user;
    }
}
