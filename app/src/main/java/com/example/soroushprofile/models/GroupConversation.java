package com.example.soroushprofile.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class GroupConversation extends ConversationThread {

    private String name;
    private Integer member;

    public GroupConversation(String name, Integer member) {
        this.name = name;
        this.member = member;
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

    public Integer getMember() {
        return member;
    }
}
