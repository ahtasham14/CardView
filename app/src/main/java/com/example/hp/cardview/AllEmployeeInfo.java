package com.example.hp.cardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



public class AllEmployeeInfo extends AppCompatActivity {

    Employee emp;
    TextView tv;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_employee_info);

        tv = (TextView)findViewById(R.id.tvJoining);
        tv1 = (TextView)findViewById(R.id.total_salary);
        emp = new Employee();

        Intent intent = getIntent();

        emp = (Employee) intent.getSerializableExtra("employeeData");
        String joining = emp.getStartingDate();
        String salary = emp.getSalary();
        tv.setText(joining);
        tv1.setText(salary);

    }
}
