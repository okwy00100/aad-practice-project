package com.okwy.practiceproject.Localization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.okwy.practiceproject.R;

import java.util.Locale;

public class LocaleActivity extends AppCompatActivity {

    Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locale);
    }

    public void setLocale(Locale myLocale){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);

        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, LocaleActivity.class);
//        finish();
        startActivity(refresh);
        finish();

    }

    public void setJapaneseLocale(View view) {
        myLocale = new Locale("ja");
        setLocale(myLocale);

    }

    public void setGermanLocale(View view) {
        myLocale = new Locale("de");
        setLocale(myLocale);
    }

    public void setFrenchLocale(View view) {
        myLocale = new Locale("fr");
        setLocale(myLocale);
    }

    public void setEnglishLocale(View view) {
        myLocale = new Locale("en");
        setLocale(myLocale);
    }
}