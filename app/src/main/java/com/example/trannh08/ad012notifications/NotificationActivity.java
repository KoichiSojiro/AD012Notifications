package com.example.trannh08.ad012notifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {

    private final String EXTRA_MESSAGE = "messages";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString(EXTRA_MESSAGE);
            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(value);
        }
    }
}
