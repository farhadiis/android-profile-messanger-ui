package com.example.soroushprofile.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.soroushprofile.R;

public class User {

    private static User INSTANCE;

    public static User getInstance() {
        if (INSTANCE == null)
            INSTANCE = new User("Farhad Azad", R.drawable.p02, true);
        return INSTANCE;
    }

    @NonNull
    private final String name;

    @Nullable
    private Integer avatar;

    @NonNull
    private final Boolean hasContent;

    public User(@NonNull String name, @Nullable Integer avatar) {
        this(name, avatar, false);
    }

    public User(@NonNull String name, @Nullable Integer avatar, @NonNull Boolean hasContent) {
        this.name = name;
        this.avatar = avatar;
        this.hasContent = hasContent;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Nullable
    public Integer getAvatar() {
        return avatar;
    }

    @NonNull
    public Boolean hasContent() {
        return hasContent;
    }
}
