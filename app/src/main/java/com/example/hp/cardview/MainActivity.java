package com.example.hp.cardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.R.*;

public class MainActivity extends AppCompatActivity {
    CardView cv;
    CardView cv2;
    CardView cv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Employee CardView Click Listner





        cv = (CardView) findViewById(R.id.cv1);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EmployeeActivity.class);
                startActivity(intent);
                finish();

            }
        });

        cv2 = (CardView) findViewById(R.id.cv2);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Projects.class);
                startActivity(intent);
                finish();

            }
        });

    }
}
