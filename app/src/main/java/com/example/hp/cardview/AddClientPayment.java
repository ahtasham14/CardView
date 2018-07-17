package com.example.hp.cardview;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.Calendar;

public class AddClientPayment extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    EditText ed3;
    EditText ed4;
    EditText ed5;
    Calendar calender;


    ArrayList<String> arrayList;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client_payment);

        calender = Calendar.getInstance();
        final int Day = calender.get(Calendar.DAY_OF_MONTH);
        final int month = calender.get(Calendar.MONTH);
        final int year = calender.get(Calendar.YEAR);


        ed3 = findViewById(R.id.editText3);
        ed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddClientPayment.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                ed3.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, year, month, Day);
                datePickerDialog.show();
            }
        });


        button = findViewById(R.id.addClient);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPaymentClient();
            }
        });


    }
        private void addPaymentClient() {
            //   progressBar.setVisibility(View.VISIBLE);

            ed1 = findViewById(R.id.editText1);
            ed2 = findViewById(R.id.editText2);
            ed3 = findViewById(R.id.editText3);
            ed4 = findViewById(R.id.editText4);
            ed5 = findViewById(R.id.editText5);

            String EditText1 = ed1.getText().toString().trim();
            String EditText2 = ed2.getText().toString().trim();
            String EditText3 = ed3.getText().toString().trim();
            String EditText4 = ed4.getText().toString().trim();
            String EditText5 = ed5.getText().toString().trim();

            String tag_string_req = "add_client_Payment";

            String url = APIClass.ClientPaymentADD+"?clientName="+EditText1+"&project="+EditText2+"&receivedDate="+EditText3+"&amountReceived="+EditText4+"&milestone="+EditText5+"";
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