package com.example.soroushprofile.prefrences;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.soroushprofile.ProfileActivity;
import com.example.soroushprofile.R;
import com.example.soroushprofile.models.ConversationThread;
import com.example.soroushprofile.models.ConversationType;
import com.example.soroushprofile.prefrences.widgets.SoroushPreference;
import com.example.soroushprofile.prefrences.widgets.SoroushPreferenceCategory;
import com.example.soroushprofile.prefrences.widgets.UserMediaPreference;
import com.example.soroushprofile.userprofile.ProfileViewModel;


public class SettingsPreference extends PreferenceFragmentCompat {

    private final static String USER_MEDIA_PREFERENCE = "user_media_preference";
    private final static String USER_MEDIA_PREFERENCE_CATEGORY = "user_media_preference_category";
    private final static String RESTRICT_PREFERENCE = "restrict_preference";
    private final static String DESCRIPTION_PREFERENCE = "description_preference";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindViewModel();
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeRestrictTextColor(findPreference(RESTRICT_PREFERENCE));
    }

    private void bindViewModel() {
        ProfileViewModel model = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        String type = requireActivity().getIntent().getStringExtra(ProfileActivity.CONVERSATION_TYPE);
        model.getConversationThread(type).observe(getViewLifecycleOwner(), this::bindThread);
    }

    private void bindThread(ConversationThread thread) {
        initializeUserMedia(thread);
        initializeDescription(thread);

        switch (thread.getType()) {
            case individual:
                initializeRestrictAction(R.string.profile_user_restrict_individual, thread.getType());
                break;
            case group:
                initializeRestrictAction(R.string.profile_user_restrict_group, thread.getType());
                break;
            case channel:
                initializeRestrictAction(R.string.profile_user_restrict_channel, thread.getType());
                break;
        }
    }


    private void initializeRestrictTextColor(Preference preference) {
        SoroushPreference soroushPreference = (SoroushPreference) preference;
        soroushPreference.setTitleColor(Color.RED);
    }

    private void initializeRestrictAction(@StringRes int title, ConversationType type) {
        Preference blockPreference = findPreference(RESTRICT_PREFERENCE);
        assert blockPreference != null;
        blockPreference.setTitle(title);
        blockPreference.setOnPreferenceClickListener(new BlockClickListener(type));
    }

    private void initializeUserMedia(ConversationThread thread) {
        SoroushPreferenceCategory category = findPreference(USER_MEDIA_PREFERENCE_CATEGORY);
        UserMediaPreference preference = findPreference(USER_MEDIA_PREFERENCE);

        assert preference != null;
        preference.refresh(thread);

        assert category != null;
        category.setOnSummaryClickListener(v -> {
            Toast.makeText(v.getContext(), "More...", Toast.LENGTH_SHORT).show();
        });
        category.setSummary(thread.getMedia().isEmpty()
                ? null : getString(R.string.profile_user_media_category_more_title));
        category.setTitle(thread.getMedia().isEmpty()
                ? null : getString(R.string.profile_user_media_category_title));
    }

    private void initializeDescription(ConversationThread thread) {
        Preference preference = findPreference(DESCRIPTION_PREFERENCE);
        assert preference != null;

        if (thread.getType().isIndividual()) {
            preference.setSummary(R.string.profile_user_description_bio_title);
            preference.setTitle(thread.getDescription().orElse(getString(R.string.profile_user_description_bio_empty)));
        } else {
            preference.setSummary(R.string.profile_user_description_title);
            preference.setTitle(thread.getDescription().orElse(getString(R.string.profile_user_description_empty)));
        }

        preference.setSummary(thread.getType().isIndividual()
                ? R.string.profile_user_description_bio_title : R.string.profile_user_description_title);
    }

    private class BlockClickListener implements Preference.OnPreferenceClickListener {

        private final ConversationType type;

        private BlockClickListener(ConversationType type) {
            this.type = type;
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            switch (type) {
                case individual:
                    Toast.makeText(getContext(), "Block Action Trigger...", Toast.LENGTH_SHORT).show();
                    break;
                case group:
                    Toast.makeText(getContext(), "Leave Group Trigger...", Toast.LENGTH_SHORT).show();
                    break;
                case channel:
                    Toast.makeText(getContext(), "Leave Channel Trigger...", Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }

    }
}
