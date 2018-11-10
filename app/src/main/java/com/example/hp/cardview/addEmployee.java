package com.example.hp.cardview;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Calendar;


public class addEmployee extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    EditText editText7;
    EditText editText8;
    Calendar calendar;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText8);

        calendar = Calendar.getInstance();
        final int Day = calendar.get(Calendar.DAY_OF_MONTH);
        final int month = calendar.get(Calendar.MONTH);
        final int year = calendar.get(Calendar.YEAR);

        editText6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(addEmployee.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                editText6.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, Day);
                datePickerDialog.show();
            }
        });


        button = findViewById(R.id.add_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployee();
            }
        });
    }
        private void addEmployee(){

            final String a = editText1.getText().toString().trim();
            final String b = editText2.getText().toString().trim();
            final String c = editText3.getText().toString().trim();
            final String d = editText4.getText().toString().trim();
            final String e = editText5.getText().toString().trim();
            final String f = editText6.getText().toString().trim();
            final String g = editText7.getText().toString().trim();
            final String h = editText8.getText().toString().trim();

        final String tag_string_req = "add_employees";

        String url = APIClass.add_record + "?name=" + a + "&title=" + b + "&total_paid=" + c + "&salary=" + d + "&email=" + e + "&starting_date="+f+"&mobilephone="+g+"&note="+h+"";
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // progressBar.setVisibility(View.GONE);

                if (a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || e.isEmpty() || f.isEmpty() || g.isEmpty() || h.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Make sure all fields to be fields.", Toast.LENGTH_SHORT).show();
                } else {
                    if (response.contains("200")) {
                        Toast.makeText(getApplicationContext(), "Data Added Succesful in Employees", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to Add Data in Employees", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(error.getMessage());
            }
        });
        MyApplication.getInstance().addToRequestQueue(stringRequest, tag_string_req);
    }
}