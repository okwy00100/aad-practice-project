package com.okwy.practiceproject.IntentsWithActivities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.okwy.practiceproject.Main.MainActivity;
import com.okwy.practiceproject.R;

public class IntentActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    public static final String EXTRA_STRING = "com.okwy.practiceproject.intentwithactivities.intentActivity";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();


    private EditText editTextMain;

    private TextView replyReceived, replyMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        editTextMain = findViewById(R.id.editTextMain);
        replyReceived = findViewById(R.id.textView6);
        replyMessage = findViewById(R.id.textViewReply);



    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "Button Clicked");
        System.out.println(">>>getName>>>" + MainActivity.class.getName());
        System.out.println(">>>getSimpleName>>>" + MainActivity.class.getSimpleName());
        System.out.println(">>>getCanonicalName>>>" + MainActivity.class.getCanonicalName());

        Intent intent = new Intent(this, SecondIntentActivity.class);
        intent.putExtra(EXTRA_STRING, editTextMain.getText().toString().trim());
        //startActivity(intent);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String reply = data.getStringExtra(SecondIntentActivity.EXTRA_REPLY);
                replyReceived.setVisibility(View.VISIBLE);
                replyMessage.setVisibility(View.VISIBLE);
                replyMessage.setText(reply);
            }
        }
    }
}