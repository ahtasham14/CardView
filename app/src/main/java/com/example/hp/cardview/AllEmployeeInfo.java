package com.example.hp.cardview;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class AllEmployeeInfo extends AppCompatActivity {

    Employee emp;
    TextView tv;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_employee_info);

        tv =  findViewById(R.id.tv_name);
        tv1 = findViewById(R.id.tv_title);
        tv2 = findViewById(R.id.tv_joining);
        tv3 = findViewById(R.id.tv_salary);
        tv4 = findViewById(R.id.tV_paid);
        tv5 = findViewById(R.id.tv_mobile);
        tv6 = findViewById(R.id.tv_email);
        tv7 = findViewById(R.id.tv_note);

        emp = new Employee();
        Intent intent = getIntent();
        emp = (Employee) intent.getSerializableExtra("employeeData");

        String Name = emp.getName();
        String Title = emp.getTitle();
        String joining = emp.getStartingDate();
        String salary = emp.getSalary();
        String paid = emp.getTotalPaid();
        final String mobile = emp.getMobile();
        String email = emp.getEmail();
        String note = emp.getNote();

        tv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(mobile));
                startActivity(intent);
            }
        });

        tv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(AllEmployeeInfo.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv.setText(Name);
        tv1.setText(Title);
        tv2.setText(joining);
        tv3.setText(salary);
        tv4.setText(paid);
        tv5.setText(mobile);
        tv6.setText(email);
        tv7.setText(note);
    }
}