package com.example.soroushprofile.models;

public enum ConversationType {

    individual, group, channel;

    public boolean isIndividual() {
        return this == individual;
    }
}
