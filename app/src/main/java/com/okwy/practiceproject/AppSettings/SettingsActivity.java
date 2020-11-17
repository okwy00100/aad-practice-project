package com.okwy.practiceproject.AppSettings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.okwy.practiceproject.R;

public class SettingsActivity extends AppCompatActivity {

    public static final String KEY_PREF_EXAMPLE_SWITCH = "example_switch";
    public static final String KEY_PREF_CHECKBOX = "check_box_preference";
    public static final String KEY_PREF_VISIBILITY_CHECKBOX = "visibility_check_box";
    public static final String KEY_PREF_LIST_PREFERENCE = "list_preference";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_settings);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}