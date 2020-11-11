package com.okwy.practiceproject.JobScheduler.Service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.okwy.practiceproject.JobScheduler.JobSchedulerActivity;
import com.okwy.practiceproject.R;

public class NotificationJobService extends JobService {

    NotificationManager notifyManager;
    private static final String PRIMARY_CHANNEL_ID = "com.okwy.practiceproject.JobScheduler.primary_notification_channel";

    @Override
    public boolean onStartJob(JobParameters params) {

        createNotificationChannel();

        PendingIntent contentPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, JobSchedulerActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setContentTitle("Job Service")
                .setContentText("Job ran to Completion")
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_job_running)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        notifyManager.notify(0, builder.build());

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return true;
    }

    private void createNotificationChannel() {
        notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Job Service Notification", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.setDescription("Notification from the Job Service");
            notificationChannel.enableVibration(true);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);

            notifyManager.createNotificationChannel(notificationChannel);
        }
    }
}
