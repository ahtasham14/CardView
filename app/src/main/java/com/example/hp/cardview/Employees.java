package com.example.hp.cardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Employees extends AppCompatActivity {

    ArrayList arrayList;
    ListView listView;
    Button button;
    ArrayList namesArray;
    ArrayList employeeRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_employees);

        arrayList = new ArrayList<>();
        namesArray = new ArrayList<>();
        employeeRecord = new ArrayList<>();

        loadEmployees();
    }
    private void loadEmployees() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, APIClass.show_record, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject product = array.getJSONObject(i);


                        namesArray.add(product.getString("name"));
                        employeeRecord.add(product.getInt("id"));
                    }

                    Intent intent = new Intent(Employees.this,EmployeeRecord.class);
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
        Volley.newRequestQueue(this).add(stringRequest);
    }
}