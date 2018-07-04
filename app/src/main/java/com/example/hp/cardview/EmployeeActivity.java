package com.example.hp.cardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.R.*;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity {
    EditText e1;
    EditText e2;
    EditText e3;
    EditText e4;
    EditText e5;
    Button button;
    Button button2;

    ArrayList<String> namesArray;   // Employee Names ArrayList
    ArrayList<Integer> employeeRecord; // Employee Record ArrayList

    ListView listView;  // ListView for Employee Name
    ArrayAdapter adapter;   // Array Adapter for Employee Names


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_);

        // Intialization of ArrayList

        namesArray = new ArrayList<String>();
        employeeRecord = new ArrayList<Integer>();

        // For Employee TextFields
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);

        listView = (ListView)findViewById(R.id.listview);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmployeeRecord();
            }
        });

        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadEmployeeNames();
            }
        });
    }

    private void addEmployeeRecord() {
     //   progressBar.setVisibility(View.VISIBLE);
        String EditText1 = e1.getText().toString().trim();
        String EditText2 = e2.getText().toString().trim();
        String EditText3 = e3.getText().toString().trim();
        String EditText4 = e4.getText().toString().trim();
        String EditText5 = e5.getText().toString().trim();

        String tag_string_req = "add_record";
        String url = APIClass.add_record + "?name="+EditText1+"&starting_date="+EditText2+"&total_paid="+EditText3+"&salary="+EditText4+"&note="+EditText5+"";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // progressBar.setVisibility(View.GONE);
                if (response.contains("200")) {
                    Toast.makeText(getApplicationContext(), "Data Added Succesful", Toast.LENGTH_SHORT).show();
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

    private void loadEmployeeNames() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, APIClass.show_record, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // First Access the Array and then Access the Objects
                            JSONArray array = new JSONArray(response);
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                JSONObject product = array.getJSONObject(i);
                                namesArray.add(product.getString("name"));
                                employeeRecord.add(product.getInt("id"));
                            }


                            Intent intent = new Intent(EmployeeActivity.this,EmployeeRecord.class);
                            intent.putStringArrayListExtra("namesArray", namesArray);
                            intent.putIntegerArrayListExtra("employeeArray",employeeRecord);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}