package com.example.trannh08.ad012notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final int NOTIFICATION_ID_NOTIFICATION01 = 1;
    private final int NOTIFICATION_ID_NOTIFICATION02 = 2;
    private final String EXTRA_MESSAGE = "messages";
    private Button button_notification01;
    private Button button_notification02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_notification01 = (Button) findViewById(R.id.button_notification01);
        button_notification01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notify(NOTIFICATION_ID_NOTIFICATION01,
                        "You've received new notification.", "Notification 01",
                        "This is the sample for Notification 01");
            }
        });

        button_notification02 = (Button) findViewById(R.id.button_notification02);
        button_notification02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notify(NOTIFICATION_ID_NOTIFICATION02,
                        "You've received new notification.", "Notification 02",
                        "This is the sample for Notification 02");
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void Notify(int notificationID, String notificationTitle, String notificationMessage,
                        String extraMessage) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra(EXTRA_MESSAGE, extraMessage);

        // using PendingIntent.FLAG_UPDATE_CURRENT to pass parameter to PendingIntent
        // default = 0
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // approach 01
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_action_name)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage)
                .setContentIntent(pendingIntent)
                .build();
        // add this flag to make notification disappear after clicking
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(notificationID, notification);

        // approach 02
//        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
//                .setSmallIcon(R.drawable.ic_action_name)
//                .setContentTitle(notificationTitle)
//                .setContentText(notificationMessage);
//        builder.setContentIntent(pendingIntent);
//        // set AutoCancel(true) to make notification disappear after clicking
//        builder.setAutoCancel(true);
//        notificationManager.notify(notificationID, builder.build());
    }
}
