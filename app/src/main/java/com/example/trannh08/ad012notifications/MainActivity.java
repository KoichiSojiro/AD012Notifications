package com.example.trannh08.ad012notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String EXTRAS_NOTIFICATION_ID = "notificationID";
    private final int NOTIFICATION_ID_NOTIFICATION01 = 1;
    private final int NOTIFICATION_ID_NOTIFICATION02 = 2;
    private final String EXTRA_MESSAGE_01 = "messages_01";
    private final String EXTRA_MESSAGE_02 = "messages_02";
    private Button button_notification01;
    private Button button_notification02;
    private int numMessages = 0;

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
                        EXTRA_MESSAGE_01, "This is the sample for Notification 01");
            }
        });

        button_notification02 = (Button) findViewById(R.id.button_notification02);
        button_notification02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Notify(NOTIFICATION_ID_NOTIFICATION02,
                        "You've received new notification.", "Notification 02",
                        EXTRA_MESSAGE_02, "This is the sample for Notification 02");
            }
        });
    }

    @SuppressWarnings("deprecation")
    private void Notify(int notificationID, String notificationTitle, String notificationMessage,
                        String extraMessageKey, String extraMessage) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, NotificationActivity.class);
        intent.putExtra(extraMessageKey, extraMessage);
        intent.putExtra(EXTRAS_NOTIFICATION_ID, notificationID);

        // using PendingIntent.FLAG_UPDATE_CURRENT to pass parameter to PendingIntent (default = 0)
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // approach 01: CLASSIC NOTIFICATION
//        Notification notification = new Notification.Builder(this)
//                .setSmallIcon(R.drawable.ic_action_name)
//                .setContentTitle(notificationTitle)
//                .setContentText(notificationMessage)
//                .setContentIntent(pendingIntent)
//                .build();
//        // add this flag to make notification disappear after clicking
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notificationManager.notify(notificationID, notification);

        // approach 02: MODERN NOTIFICATION
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_action_name)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage);
        builder.setContentIntent(pendingIntent);
        // show number of notifications were showing
        builder.setNumber(++numMessages);
        // create an Inbox Style
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        String[] events = new String[6];
        events[0] = new String("This is 1st line....");
        events[1] = new String("This is 2ne line...");
        events[2] = new String("This is 3rd line...");
        events[3] = new String("This is 4th line...");
        events[4] = new String("This is 5th line...");
        events[5] = new String("This is 6th line...");
        // Sets a title for the Inbox style big view
        inboxStyle.setBigContentTitle(notificationTitle + " details:");
        // Moves events into the big view
        for (int i=0; i < events.length; i++) {
            inboxStyle.addLine(events[i]);
        }
        // tell program that NotificationCompat.Builder is using InboxStyle
        builder.setStyle(inboxStyle);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationActivity.class);
        /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, 0);
        builder.setContentIntent(resultPendingIntent);
        // set AutoCancel(true) to make notification disappear after clicking
        builder.setAutoCancel(true);
        notificationManager.notify(notificationID, builder.build());
    }
}
