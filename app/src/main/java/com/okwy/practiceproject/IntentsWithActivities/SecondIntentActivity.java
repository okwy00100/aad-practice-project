package com.okwy.practiceproject.IntentsWithActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.okwy.practiceproject.Main.MainActivity;
import com.okwy.practiceproject.R;

public class SecondIntentActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.okwy.practiceproject.intentswithactivities.secondintentactivity.extra.REPLY";
    private TextView replyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_intent);

        TextView textViewExtra = findViewById(R.id.textViewExtra);
        textViewExtra.setText(getIntent().getStringExtra(IntentActivity.EXTRA_STRING));

        replyEditText = findViewById(R.id.replyEditText);


    }

    public void returnReply(View view) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, replyEditText.getText().toString());
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}