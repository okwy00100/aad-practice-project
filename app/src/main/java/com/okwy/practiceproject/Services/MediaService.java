package com.okwy.practiceproject.Services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.okwy.practiceproject.R;

public class MediaService extends Service {

    public MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        playMedia();
        return START_STICKY;

    }

    private void playMedia() {
        System.out.println(">>>>>>Inside PlayMedia method in Media Service>>>>>>");
        player = MediaPlayer.create(this, R.raw.killlakill);

        Intent redirectIntent = new Intent(this, ServiceActivity.class);
        PendingIntent redirectPendingIntent = PendingIntent.getActivity(this, 0, redirectIntent, 0);

        Intent playActionIntent = new Intent(ServiceActivity.PLAY_MEDIA);
        Intent stopActionIntent = new Intent(ServiceActivity.STOP_MEDIA);

        PendingIntent playActionPendingIntent = PendingIntent.getBroadcast(this, 0, playActionIntent, 0);
        PendingIntent stopActionPendingIntent = PendingIntent.getBroadcast(this, 0, stopActionIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, ServiceActivity.SERVICE_CHANNEL_ID)
                .setContentIntent(redirectPendingIntent)
                .setContentText("Song is playing now...")
                .setContentTitle("My Media Service")
                .setSmallIcon(R.drawable.ic_music)
                .addAction(R.drawable.ic_play, "Play Song", playActionPendingIntent)
                .addAction(R.drawable.ic_stop, "Stop Song", stopActionPendingIntent)
                .setOngoing(true)
                .build();

        //NotificationManagerCompat.from(this).notify(101, notification);

        player.start();
        startForeground(101, notification);
        Toast.makeText(this, "Media started!!", Toast.LENGTH_SHORT).show();

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        Toast.makeText(this, "Media Stopped", Toast.LENGTH_SHORT).show();
    }
}