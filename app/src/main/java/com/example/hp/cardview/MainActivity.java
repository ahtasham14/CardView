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
    CardView cv4;
    CardView cv5;
    CardView cv6;
    CardView cv7;
    CardView cv8;



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

        cv3 = findViewById(R.id.cv3);
        cv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Assest.class);
                startActivity(intent);
                finish();
            }
        });

        cv4 = findViewById(R.id.cv4);
        cv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Credentials.class);
                startActivity(intent);
                finish();
            }
        });
        cv5 = findViewById(R.id.cv5);
        cv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), ClientPayment.class);
                startActivity(intent);
                finish();

            }
        });

        cv6 = findViewById(R.id.cv6);
        cv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), PaymentEmployees.class);
                startActivity(intent);
                finish();
            }
        });

        cv7 = findViewById(R.id.cv7);
        cv7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Notes.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
