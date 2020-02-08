package com.example.soroushprofile.prefrences;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.soroushprofile.R;
import com.example.soroushprofile.prefrences.widgets.SoroushPreference;

import java.util.Objects;

public class SettingsPreference extends PreferenceFragmentCompat {

    private final static String USER_MEDIA_PREFERENCE           = "user_media_preference";
    private final static String USER_MEDIA_PREFERENCE_CATEGORY  = "user_media_preference_category";
    private final static String BLOCK_PREFERENCE                = "block_preference";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Preference blockPreference = findPreference(BLOCK_PREFERENCE);

        Objects.requireNonNull(blockPreference).setOnPreferenceClickListener(new BlockClickListener());

        initializeUserMedia(findPreference(USER_MEDIA_PREFERENCE));
        initializeBlockAction(findPreference(BLOCK_PREFERENCE));
    }

    private void initializeBlockAction(Preference preference) {
        SoroushPreference soroushPreference = (SoroushPreference) preference;
        soroushPreference.setTitleColor(Color.RED);
    }

    private void initializeUserMedia(Preference preference) {
//        UserMediaPreference userMediaPreference = (UserMediaPreference) preference;
//        userMediaPreference.refresh(User.getInstance());
//
//        Preference category = findPreference(USER_MEDIA_PREFERENCE_CATEGORY);
//        Objects.requireNonNull(category).setTitle(User.getInstance().hasContent()
//                ? getString(R.string.profile_user_media_category_title) : null);
    }

    private class BlockClickListener implements Preference.OnPreferenceClickListener {

        @Override
        public boolean onPreferenceClick(Preference preference) {
            Toast.makeText(getContext(), "Block Action Trigger...", Toast.LENGTH_SHORT).show();
            return true;
        }

    }
}
