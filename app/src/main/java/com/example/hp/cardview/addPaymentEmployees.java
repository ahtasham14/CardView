package com.example.hp.cardview;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.Calendar;

public class addPaymentEmployees extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    EditText ed3;
    Button button;
    Calendar calender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_employees);

        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);
        ed3 = findViewById(R.id.editText3);

        calender = Calendar.getInstance();
        final int Day = calender.get(Calendar.DAY_OF_MONTH);
        final int month = calender.get(Calendar.MONTH);
        final int year = calender.get(Calendar.YEAR);

        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(addPaymentEmployees.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                ed2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, Day);
                datePickerDialog.show();
            }
        });

        button = findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPaymentEmployees();

            }
        });
    }

    private void addPaymentEmployees() {
        //   progressBar.setVisibility(View.VISIBLE);

        String EditText1 = ed1.getText().toString().trim();
        String EditText2 = ed2.getText().toString().trim();
        String EditText3 = ed3.getText().toString().trim();

        String tag_string_req = "add_payment_employees";

        String url = APIClass.AddEmployeePaymet+"?employeeName="+EditText1+"&receiveDate="+EditText2+"&amountPaid="+EditText3+"";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // progressBar.setVisibility(View.GONE);
                if (response.contains("200")) {
                    Toast.makeText(getApplicationContext(), "Data Added Succesful in Assets", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to Add Data", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Server Error", Toast.LENGTH_SHORT).show();
            }
        });
        MyApplication.getInstance().addToRequestQueue(stringRequest, tag_string_req);

    }
}