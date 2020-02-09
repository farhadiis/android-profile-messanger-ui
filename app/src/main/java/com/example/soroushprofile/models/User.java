package com.example.soroushprofile.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.annimon.stream.Optional;

public class User {

    @NonNull
    private final String name;

    @Nullable
    private Integer avatar;

    @Nullable
    private Optional<String> bio;


    public User(@NonNull String name, @Nullable Integer avatar, Optional<String> bio) {
        this.name = name;
        this.avatar = avatar;
        this.bio = bio;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public Integer getAvatar() {
        return avatar;
    }

    @Nullable
    public Optional<String> getBio() {
        return bio;
    }
}
