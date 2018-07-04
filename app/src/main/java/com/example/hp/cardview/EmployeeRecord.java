package com.example.hp.cardview;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class EmployeeRecord extends AppCompatActivity {

    ArrayList<String> arrayList;
    ArrayList<Integer> idList;
    ArrayAdapter arrayAdapter;
    ListView listView;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__record);

        Intent intent = getIntent();
        arrayList = intent.getStringArrayListExtra("namesArray");
        idList = intent.getIntegerArrayListExtra("employeeArray");
        listView = findViewById(R.id.listview);

        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int id = idList.get(i);
                loadEmployees(id);
            }
        });

    }

    private void loadEmployees(int id) {

        String url = APIClass.show_specific_record+"?id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    // First Access the Array and then Access the Objects
                    JSONArray array = new JSONArray(response);

                    //traversing through all the object
                    JSONObject product = array.getJSONObject(0);

                    employee = new Employee();
                    employee.setId(product.getString("id"));
                    employee.setName(product.getString("name"));
                    employee.setStartingDate(product.getString("starting_date"));
                    employee.setTotalPaid(product.getString("total_paid"));
                    employee.setSalary(product.getString("salary"));
                    employee.setNote(product.getString("note"));

                    System.out.println(employee.toString());

                    Intent intent = new Intent(getApplicationContext(), AllEmployeeInfo.class);
                    intent.putExtra("employeeData", employee);
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
