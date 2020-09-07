package com.okwy.practiceproject.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.okwy.practiceproject.R;

public class ToastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
    }

    public void testToast(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Testing Toast", Toast.LENGTH_SHORT);
        View v = toast.getView();
        //v.setBackgroundColor(Color.BLUE);
        v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
        TextView text = v.findViewById(android.R.id.message);
        text.setTextColor(Color.WHITE);
//        toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 200);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void testCustomToast(View view) {
        Log.i("Custom toast", "Inside method");
        View layout = getLayoutInflater().inflate(R.layout.activity_toast_custom, (ViewGroup) findViewById(R.id.toast_custom));
        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);
        toast.show();
        Log.i("Custom toast", "Past toast.show()");

    }
}