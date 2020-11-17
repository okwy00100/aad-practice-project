package com.okwy.practiceproject.AppSettings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;



import com.okwy.practiceproject.R;


public class SettingsFragment extends PreferenceFragmentCompat {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }



}