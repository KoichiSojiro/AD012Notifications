package com.example.trannh08.ad012notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    private final String EXTRAS_NOTIFICATION_ID = "notificationID";
    private final String EXTRA_MESSAGE_01 = "messages_01";
    private final String EXTRA_MESSAGE_02 = "messages_02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        TextView textView = (TextView) findViewById(R.id.textView);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int value = extras.getInt(EXTRAS_NOTIFICATION_ID);
            switch (value)
            {
                case 1:
                    String str = extras.getString(EXTRA_MESSAGE_01);
                    textView.setText(str);
                    break;
                case 2:
                    String str2 = extras.getString(EXTRA_MESSAGE_02);
                    textView.setText(str2);
                    break;
            }
        }
    }
}
