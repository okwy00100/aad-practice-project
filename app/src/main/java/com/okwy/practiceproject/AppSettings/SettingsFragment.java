package com.okwy.practiceproject.AppSettings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;


import com.okwy.practiceproject.R;


public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

        SharedPreferences sharedPref = getPreferenceScreen().getSharedPreferences();

        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount();

        for(int i = 0; i < count; i++){
            Preference pref = prefScreen.getPreference(i);
            if(pref instanceof ListPreference){
                String value = sharedPref.getString(pref.getKey(), "");
                setPreferenceSummary(pref, value);
            }
        }
    }

    private void setPreferenceSummary(Preference preference, String value){
        if(preference instanceof ListPreference){
            ListPreference listPreference = (ListPreference) preference;
            int prefValue = listPreference.findIndexOfValue(value);
            if(prefValue >= 0){
                listPreference.setSummary(listPreference.getEntries()[prefValue]);
            }
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if(null != preference && preference instanceof ListPreference){
            String value = sharedPreferences.getString(preference.getKey(), "");
            setPreferenceSummary(preference, value);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }
}