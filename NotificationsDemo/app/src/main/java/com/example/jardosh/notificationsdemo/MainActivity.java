package com.example.jardosh.notificationsdemo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1,intent, 0);

            String CHANNEL_ID = "Main Notifications Channel";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                // Create the NotificationChannel
                CharSequence name = "Basic Notifications";
                String description = "This Channel is for Notifications of the basic notification category. User sees this in the system settings.";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                mChannel.setDescription(description);
                // Register the channel with the system; you can't change the importance
                // or other notification behaviors after this
                NotificationManager notificationManager = (NotificationManager) getSystemService(
                        NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(mChannel);
            }



            Notification notification = new Notification.Builder(getApplicationContext())
                    .setContentTitle("This is the Notification!")
                    .setContentText("This is the text from the note")
                    .setContentIntent(pendingIntent)
                    .addAction(android.R.drawable.sym_action_chat, "Chat", pendingIntent)
                    .setSmallIcon(android.R.drawable.sym_def_app_icon)
                    .build();

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.notify(1, notification);

    }
}
