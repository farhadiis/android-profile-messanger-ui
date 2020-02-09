package com.example.soroushprofile.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.annimon.stream.Optional;

import java.util.List;

public abstract class ConversationThread {

    @NonNull
    public abstract String getTitle();

    @Nullable
    public abstract Integer getAvatar();

    @NonNull
    public abstract ConversationType getType();

    public abstract Optional<List<String>> getMedia();

    public abstract Optional<String> getDescription();
}
