package com.okwy.practiceproject.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.okwy.practiceproject.R;

public class SnackbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
    }

    public void testSnackbar(View view) {
       // Snackbar.make(view, "Hello there", Snackbar.LENGTH_LONG).show();

        Snackbar snackBar = Snackbar.make(view, "Hello there", Snackbar.LENGTH_LONG);
        snackBar.setActionTextColor(Color.BLUE);
        View snackBarView = snackBar.getView();
//        TextView textView = snackBarView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(Color.RED);
        snackBar.show();
    }
}