package com.okwy.practiceproject.Main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.okwy.practiceproject.AppSettings.AppSettingsActivity;
import com.okwy.practiceproject.DrawablesStylesThemes.ScoreKeeperActivity;
import com.okwy.practiceproject.IntentsWithActivities.IntentActivity;
import com.okwy.practiceproject.JobScheduler.JobSchedulerActivity;
import com.okwy.practiceproject.Localization.LocaleActivity;
import com.okwy.practiceproject.Main.Adapter.MainAdapter;
import com.okwy.practiceproject.Notification.NotificationActivity;
import com.okwy.practiceproject.R;
import com.okwy.practiceproject.Snackbar.SnackbarActivity;
import com.okwy.practiceproject.TabNavigation.TabNavigationActivity;
import com.okwy.practiceproject.Toast.ToastActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainAdapter.ListItemClickListener{

    private RecyclerView mainRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainRecycler = findViewById(R.id.mainRecycler);

        displayRecyclerView();
    }

    private void displayRecyclerView() {
        List<String> conceptList = new ArrayList<>();
        conceptList.add("Toast");
        conceptList.add("Snackbar");
        conceptList.add("Notification");
        conceptList.add("Localization");
        conceptList.add("JobScheduler");
        conceptList.add("App Settings");
        conceptList.add("Tab Navigation");
        conceptList.add("Intents and Activities");
        conceptList.add("Drawables, Styles and Themes");

        System.out.println(conceptList.toString());
        MainAdapter mainAdapter = new MainAdapter(this, conceptList, MainActivity.this);
        mainRecycler.setLayoutManager(new LinearLayoutManager(this));
        mainRecycler.setAdapter(mainAdapter);
    }


    @Override
    public void onListItemClick(String menuItem, int position) {
        switch (menuItem){
            case "Toast":
                startActivity(new Intent(this, ToastActivity.class));
                break;
            case "Snackbar":
                startActivity(new Intent(this, SnackbarActivity.class));
                break;
            case "Notification":
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case "Localization":
                startActivity(new Intent(this, LocaleActivity.class));
                break;
            case "JobScheduler":
                startActivity(new Intent(this, JobSchedulerActivity.class));
                break;
            case "App Settings":
                startActivity(new Intent(this, AppSettingsActivity.class));
                break;
            case "Tab Navigation":
                startActivity(new Intent(this, TabNavigationActivity.class));
                break;
            case "Intents and Activities":
                startActivity(new Intent(this, IntentActivity.class));
                break;
            case "Drawables, Styles and Themes":
                startActivity(new Intent(this, ScoreKeeperActivity.class));
                break;
        }
    }
}