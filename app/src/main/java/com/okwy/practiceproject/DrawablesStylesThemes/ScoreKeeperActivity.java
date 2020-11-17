package com.okwy.practiceproject.DrawablesStylesThemes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.okwy.practiceproject.R;

public class ScoreKeeperActivity extends AppCompatActivity {

    private int scoreOne;
    private int scoreTwo;

    private TextView scoreTextOne;
    private TextView scoreTextTwo;

    static String STATE_SCORE_ONE = "State Score 1";
    static String STATE_SCORE_TWO = "State Score 2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_keeper);

        scoreTextOne = findViewById(R.id.score_1);
        scoreTextTwo = findViewById(R.id.score_2);

        if(savedInstanceState != null){
            scoreOne = savedInstanceState.getInt(STATE_SCORE_ONE);
            scoreTwo = savedInstanceState.getInt(STATE_SCORE_TWO);
            scoreTextOne.setText(String.valueOf(scoreOne));
            scoreTextTwo.setText(String.valueOf(scoreTwo));
        }
    }

    public void decreaseScore(View view) {
        int viewID = view.getId();

        switch(viewID){
            case R.id.decreaseTeam1:
                if(scoreOne != 0) {
                    scoreOne--;
                    scoreTextOne.setText(String.valueOf(scoreOne));
                }
                break;
            case R.id.decreaseTeam2:
                if(scoreTwo != 0) {
                    scoreTwo--;
                    scoreTextTwo.setText(String.valueOf(scoreTwo));
                }
                break;
        }
    }

    public void increaseScore(View view) {
        int viewID = view.getId();

        switch(viewID){
            case R.id.increaseTeam1:
                scoreOne++;
                scoreTextOne.setText(String.valueOf(scoreOne));
                break;
            case R.id.increaseTeam2:
                scoreTwo++;
                scoreTextTwo.setText(String.valueOf(scoreTwo));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scorekeeper_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.night_mode){
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putInt(STATE_SCORE_ONE, scoreOne);
        outState.putInt(STATE_SCORE_TWO, scoreTwo);

        super.onSaveInstanceState(outState);
    }
}