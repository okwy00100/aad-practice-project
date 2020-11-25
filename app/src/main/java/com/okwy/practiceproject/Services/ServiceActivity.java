package com.okwy.practiceproject.Services;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.okwy.practiceproject.Notification.NotificationActivity;
import com.okwy.practiceproject.R;

public class ServiceActivity extends AppCompatActivity {

    public static final String SERVICE_CHANNEL_ID = "my_service_channel_id";

    public static final String PLAY_MEDIA = "play_media";
    public static final String STOP_MEDIA = "stop_media";

    private IntentFilter intentFilter;
    private ServiceBroadcastReceiver serviceBroadcastReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        intentFilter = new IntentFilter();
        intentFilter.addAction(PLAY_MEDIA);
        intentFilter.addAction(STOP_MEDIA);

        serviceBroadcastReceiver = new ServiceBroadcastReceiver();

        createNotificationChannel();

        registerReceiver(serviceBroadcastReceiver, intentFilter);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(serviceBroadcastReceiver);
    }

    public void startMediaService(View view) {
        System.out.println(">>>>>>Inside startMediaService method in ServiceActivity>>>>>>");
        Intent serviceIntent = new Intent(this, MediaService.class);
        startService(serviceIntent);
    }

    public void stopMediaService(View view) {
        System.out.println(">>>>>>Inside startMediaService method in ServiceActivity>>>>>>");
        Intent serviceIntent = new Intent(this, MediaService.class);
        stopService(serviceIntent);
    }


    private void createNotificationChannel(){
        NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(SERVICE_CHANNEL_ID, "My Media Channel Notifier", NotificationManager.IMPORTANCE_HIGH);

            notifyManager.createNotificationChannel(channel);
        }
    }

    public class ServiceBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(PLAY_MEDIA) && !isMyServiceRunning(MediaService.class)){
                Intent serviceIntent = new Intent(ServiceActivity.this, MediaService.class);
                startService(serviceIntent);
            }else if(intent.getAction().equals(STOP_MEDIA)){
                Intent serviceIntent = new Intent(ServiceActivity.this, MediaService.class);
                stopService(serviceIntent);
            }else{
                Toast.makeText(ServiceActivity.this, "Media Service already running", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}