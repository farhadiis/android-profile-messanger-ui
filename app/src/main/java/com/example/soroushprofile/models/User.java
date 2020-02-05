package com.example.soroushprofile.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class User {

    @NonNull
    private final String name;

    @Nullable
    private Integer avatar;

    public User(@NonNull String name, @Nullable Integer avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public Integer getAvatar() {
        return avatar;
    }
}
