package com.example.jardosh.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    NotificationCompat.Builder notification;
    private static final int UniqueId = 46132;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notification = new NotificationCompat.Builder(this, "Chanel")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Event tracker")
                .setContentText("Events received");;
        notification.setAutoCancel(true);
    }

    public void dhavalButtonClicked(View view){
        //Build Notification
        Uri alarmSound = RingtoneManager.getActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION);
        notification.setSound(alarmSound);
        notification.setSmallIcon(R.mipmap.ic_launcher_round);
        notification.setWhen(System.currentTimeMillis());
        notification.setTicker("BMFC Game Set");
        notification.setContentTitle("BMFC v/s Surat FC");
        notification.setContentText("Bhagwan Mahavir FC will play Surat FC on 24th Feb, 2018 at Vicky Jakaas at 6:00 PM");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        //Builds notification and issues it
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(UniqueId, notification.build());

    }
}
