package com.okwy.practiceproject.JobScheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.okwy.practiceproject.JobScheduler.Service.NotificationJobService;
import com.okwy.practiceproject.R;

public class JobSchedulerActivity extends AppCompatActivity {

    RadioGroup networkOptions;
    private JobScheduler jobScheduler;
    private Switch idleSwitch;
    private Switch chargingSwitch;

    private static final int JOB_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_scheduler);

        networkOptions = findViewById(R.id.networkOptions);
        idleSwitch = findViewById(R.id.idleSwitch);
        chargingSwitch = findViewById(R.id.chargingSwitch);
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);

    }

    public void scheduleJobs(View view) {
        int selectedNetworkID = networkOptions.getCheckedRadioButtonId();
        int selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;

        switch (selectedNetworkID) {
            case R.id.noNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE;
                break;
            case R.id.anyNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY;
                break;
            case R.id.wifiNetwork:
                selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED;
                break;
            default:
                break;
        }

        ComponentName serviceName = new ComponentName(getPackageName(), NotificationJobService.class.getName());
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(selectedNetworkOption)
                .setRequiresDeviceIdle(idleSwitch.isChecked())
                .setRequiresCharging(chargingSwitch.isChecked());

        boolean constraintSet = (selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE) || idleSwitch.isChecked() || chargingSwitch.isChecked();

        if(constraintSet){
            JobInfo jobInfo = builder.build();
            jobScheduler.schedule(jobInfo);

            Toast.makeText(this, "Job Scheduled. It will run when constraints are met.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Please select at least one constraint", Toast.LENGTH_LONG).show();
        }



    }

    public void cancelJobs(View view) {
        if(jobScheduler != null){
            jobScheduler.cancelAll();
            jobScheduler = null;
            Toast.makeText(this, "Job cancelled", Toast.LENGTH_LONG).show();
        }
    }
}