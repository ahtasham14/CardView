package com.example.hp.cardview;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.sql.Array;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

public class addProject extends AppCompatActivity {
    Spinner spinner;
    ArrayList<String> arrayList;

    Spinner spinner1;
    ArrayList<String> arrayList1;

    TextView textView;
    TextView textView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        spinner = findViewById(R.id.spinner);
        Calendar calender;
        arrayList = new ArrayList<>();
        arrayList.add("Fiverr");
        arrayList.add("Upwork");
        arrayList.add("FreeLancer");
        arrayList.add("TopTell");

        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList);
        spinner.setAdapter(spinnerAdapter);

        spinner = findViewById(R.id.spinner2);

        arrayList = new ArrayList<>();
        arrayList.add("Usama");
        arrayList.add("Muneeb");
        arrayList.add("Hashim");
        arrayList.add("Kaleem");

        ArrayAdapter spinnerAdapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList);
        spinner.setAdapter(spinnerAdapter2);

        calender = Calendar.getInstance();
        final int Day = calender.get(Calendar.DAY_OF_MONTH);
        final int month = calender.get(Calendar.MONTH);
        final int year = calender.get(Calendar.YEAR);


        textView = findViewById(R.id.tv1);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(addProject.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                textView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, year, month, Day);
                datePickerDialog.show();
            }
        });



        textView2 = findViewById(R.id.tv2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(addProject.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                textView2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, year, month, Day);
                datePickerDialog.show();
            }
        });
    }
}