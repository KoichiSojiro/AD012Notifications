package com.example.trannh08.ad012notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notify("You've received new notification.", "Hello World!");
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void Notify(String notificationTitle, String notificationMessage) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

//        Notification notification = new Notification(R.drawable.ic_action_name, "New notification", System.currentTimeMillis());
//        notification.setLatestEventInfo(MainActivity.this, notificationTitle,notificationMessage, pendingIntent);

        // approach 01
        Notification notification = new Notification.Builder(this)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage)
                .setSmallIcon(R.drawable.ic_action_name)
                .build();
        notificationManager.notify(1, notification);

        // approach 02
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_action_name)
            .setContentTitle(notificationTitle)
            .setContentText(notificationMessage);
        builder.setContentIntent(pendingIntent);
        notificationManager.notify(1, builder.build());
    }
}
