package com.example.hp.cardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Reminder extends AppCompatActivity {

    ImageView attendence;
    ImageView sendReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);


        attendence = findViewById(R.id.attendence);
        sendReminder = findViewById(R.id.sendReminder);

        attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), attendence.class);
                startActivity(intent);
                finish();
            }
        });

        sendReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startService(new Intent(getApplicationContext(), MyService.class));
            }
        });
    }
}
