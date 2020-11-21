package com.okwy.practiceproject.AppSettings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.okwy.practiceproject.R;

public class AppSettingsActivity extends AppCompatActivity  {

    private ConstraintLayout layout;

    private Boolean switchPref;
    private Boolean checkboxPref;
    private Boolean visibilityPref;
    private String backgroundPref;

    private TextView appText;
    private TextView hiddenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);

        //PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        layout = findViewById(R.id.layout);

        appText = findViewById(R.id.appText);
        hiddenText = findViewById(R.id.hiddenText);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        switchPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_EXAMPLE_SWITCH, false);
        checkboxPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_CHECKBOX, false);
        visibilityPref = sharedPref.getBoolean(SettingsActivity.KEY_PREF_VISIBILITY_CHECKBOX, false);

        backgroundPref = sharedPref.getString(SettingsActivity.KEY_PREF_LIST_PREFERENCE, "Default");

        switch(backgroundPref){
            case "Default":
                layout.setBackgroundColor(Color.WHITE);
                appText.setTextColor(Color.BLACK);
                hiddenText.setTextColor(Color.BLACK);
                break;
            case "Red":
                layout.setBackgroundColor(Color.RED);
                appText.setTextColor(Color.WHITE);
                hiddenText.setTextColor(Color.WHITE);
                break;
            case "Green":
                layout.setBackgroundColor(Color.GREEN);
                appText.setTextColor(Color.WHITE);
                hiddenText.setTextColor(Color.WHITE);
                break;
            case "Blue":
                layout.setBackgroundColor(Color.BLUE);
                appText.setTextColor(Color.WHITE);
                hiddenText.setTextColor(Color.WHITE);
                break;
            case "Black":
                layout.setBackgroundColor(Color.BLACK);
                appText.setTextColor(Color.WHITE);
                hiddenText.setTextColor(Color.WHITE);
                break;
        }



        Toast.makeText(this, switchPref.toString(), Toast.LENGTH_LONG).show();

        if (visibilityPref) {
            hiddenText.setVisibility(View.VISIBLE);
        } else {
            hiddenText.setVisibility(View.INVISIBLE);
        }

        //sharedPref.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        if(key.equalsIgnoreCase(SettingsActivity.KEY_PREF_EXAMPLE_SWITCH)){
//            Toast.makeText(this, switchPref.toString(), Toast.LENGTH_LONG).show();
//        }else if(key.equalsIgnoreCase(SettingsActivity.KEY_PREF_VISIBILITY_CHECKBOX)){
//            if (visibilityPref) {
//                hiddenText.setVisibility(View.VISIBLE);
//            } else {
//                hiddenText.setVisibility(View.INVISIBLE);
//            }
//        }
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
//    }
}