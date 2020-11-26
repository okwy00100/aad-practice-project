package com.okwy.practiceproject.WorkManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.okwy.practiceproject.R;

public class MyWorker extends Worker {

    public static final String KEY_OUTPUT_DESC = "key_output_desc";

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        displayNotification(getInputData().getString(WorkManagerActivity.KEY_INPUT_DESC));

        Data outData = new Data.Builder()
                .putString(KEY_OUTPUT_DESC, "Task Complete")
                .build();


        return Result.success(outData);
    }


    private void displayNotification(String desc){
        NotificationManager notifyManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_HIGH);
            notifyManager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(getApplicationContext(), "channel_id")
                .setContentTitle("Workmanager")
                .setContentText(desc+ "\nThis is the work you made")
                .setSmallIcon(R.drawable.ic_job_running)
                .build();

        notifyManager.notify(109, notification);
    }
}
