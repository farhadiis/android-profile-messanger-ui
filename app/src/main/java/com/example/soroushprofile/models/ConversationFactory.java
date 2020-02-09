package com.example.soroushprofile.models;

import com.annimon.stream.Optional;
import com.example.soroushprofile.R;

import java.util.ArrayList;
import java.util.List;

public final class ConversationFactory {

    public static ConversationThread getThread(ConversationType type) {
        switch (type) {
            case individual:
                String bio = "27 y.o. designer from tehran, iran..., The Biotechnology Innovation Organization\n" +
                        " is the largest trade organization in the world that represents the biotechnology industry.";
                User user = new User("Farhad Azad", R.drawable.p02, Optional.of(bio));
                List<String> media = new ArrayList<String>() {{
                    add("1.jpg");
                    add("2.jpg");
                }};
                return new IndividualConversation(Optional.of(media), user);
            case group:
                return new GroupConversation(Optional.empty(), Optional.empty(), "Soroush", 231);
            case channel:
                return new ChannelConversation(Optional.empty(), Optional.of("khabar 24 is live.."), "Khabar24", 429);
            default:
                throw new IllegalArgumentException("not supported type.");
        }
    }

}
