package com.example.soroushprofile.models;

public final class ConversationFactory {

    public static ConversationThread getThread(ConversationType type) {
        switch (type) {
            case individual:
                return new IndividualConversation(User.getInstance(true));
            case group:
                return new GroupConversation("Soroush", 231);
            case channel:
                return new ChannelConversation("Khabar24", 429);
            default:
                throw new IllegalArgumentException("not supported type.");
        }
    }

}
