package com.okwy.practiceproject.Notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.okwy.practiceproject.R;

public class NotificationActivity extends AppCompatActivity {

    private Button button_notify;
    private Button button_update;
    private Button button_cancel;

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final String ACTION_UPDATE_NOTIFICATION = "com.okwy.practiceproject.notification.ACTION_UPDATE_NOTIFICATION";
    private static final int NOTIFICATION_ID = 0;
    private NotificationManager notifyManager;
    private NotificationReceiver receiver = new NotificationReceiver();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        button_notify = findViewById(R.id.notify);
        button_update = findViewById(R.id.update);
        button_cancel = findViewById(R.id.cancel);

        button_notify.setOnClickListener(v -> sendNotification());
        button_update.setOnClickListener(v -> updateNotification());
        button_cancel.setOnClickListener(v -> cancelNotification());

        createNotificationChannel();
        registerReceiver(receiver, new IntentFilter(ACTION_UPDATE_NOTIFICATION));
    }


    private void sendNotification() {
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        Notification notification = getNotificationBuilder()
                .addAction(R.drawable.ic_update, "Update Notification", updatePendingIntent)
                .build();

        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification);
    }

    private void updateNotification() {
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.mascot);
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        Notification notification = notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(image)
                .setBigContentTitle("Notification Updated!!!"))

                .build();
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, notification);
    }

    private void cancelNotification() {
        notifyManager.cancel(NOTIFICATION_ID);
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(receiver);
        super.onDestroy();
    }


    private NotificationCompat.Builder getNotificationBuilder() {
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        return new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_emoji)
                .setContentTitle("Hey there")
                .setContentText("You have just been notified... hehe")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

    }


    private void createNotificationChannel() {
        notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //create a notification channel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID, "My Notification", NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("This is a simple notification test");

            notifyManager.createNotificationChannel(notificationChannel);

        }
    }


    public class NotificationReceiver extends BroadcastReceiver {

        public NotificationReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification();
        }
    }
}