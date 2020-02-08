package com.example.soroushprofile.prefrences;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.soroushprofile.R;
import com.example.soroushprofile.models.User;
import com.example.soroushprofile.prefrences.widgets.UserMediaPreference;

import java.util.Objects;

public class SettingsPreference extends PreferenceFragmentCompat {

    private final static String USER_MEDIA_PREFERENCE = "user_media_preference";
    private final static String USER_MEDIA_PREFERENCE_CATEGORY = "user_media_preference_category";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeUserMedia(findPreference(USER_MEDIA_PREFERENCE));
    }

    private void initializeUserMedia(Preference preference) {
        UserMediaPreference userMediaPreference = (UserMediaPreference) preference;
        userMediaPreference.refresh(User.getInstance());

        Preference category = findPreference(USER_MEDIA_PREFERENCE_CATEGORY);
        Objects.requireNonNull(category).setTitle(User.getInstance().hasContent()
                ? getString(R.string.profile_user_media_category_title) : null);
    }
}
