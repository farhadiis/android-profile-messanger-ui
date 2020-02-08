package com.example.soroushprofile.prefrences;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.soroushprofile.R;

public class SettingsPreference extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }

}
