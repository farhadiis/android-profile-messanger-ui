package com.example.soroushprofile.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.annimon.stream.Optional;

import java.util.List;

public class GroupConversation extends ConversationThread {

    private String name;
    private Integer member;
    private Optional<List<String>> media;
    private Optional<String> description;

    GroupConversation(Optional<List<String>> media, Optional<String> description, String name, Integer member) {
        this.name = name;
        this.member = member;
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
        return ConversationType.group;
    }

    @Override
    public Optional<List<String>> getMedia() {
        return media;
    }

    @Override
    public Optional<String> getDescription() {
        return description;
    }

    public Integer getMember() {
        return member;
    }
}
