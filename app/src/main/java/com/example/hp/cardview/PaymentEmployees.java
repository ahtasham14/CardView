package com.example.hp.cardview;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PaymentEmployees extends AppCompatActivity {

    ListView ListView;
    Button button;
    java.util.ArrayList ArrayList;
    ArrayAdapter arrayAdapter;

    Dialog alertDialog;
    ArrayList forID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_employees);

        alertDialog = new Dialog(this);
        ArrayList = new ArrayList<>();
        forID = new ArrayList<>();

        ListView = findViewById(R.id.employeeList);
        button = findViewById(R.id.paymentEmployees);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), addPaymentEmployees.class);
                startActivity(intent);
            }
        });
        loadPaymentEmployees();

    }
        private void loadPaymentEmployees() {

            String url = APIClass.PaymentEmployees;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++){

                            JSONObject product = array.getJSONObject(i);

                        ArrayList.add(product.getString("employeeName"));
                        forID.add(product.getInt("id"));

                    }
                        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override

                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                int id = (int) forID.get(i);
                                EmployeePaymentBYID(id);

                            }
                        });
                        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,ArrayList);
                        ListView.setAdapter(arrayAdapter);

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

    private void EmployeePaymentBYID(int id) {

        String url = APIClass.EmployeePaymentByID+"?id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);
                    JSONObject product = array.getJSONObject(0);

                    String employeeName = product.getString("employeeName");
                    String receiveDate = product.getString("receiveDate");
                    String amountPaid =  product.getString("amountPaid");

                    alertDialog.setContentView(R.layout.custom_layout_employee_payment);

                    TextView textView =   alertDialog.findViewById(R.id.tv1);
                    TextView textView2 =  alertDialog.findViewById(R.id.tv2);
                    TextView textView3 =  alertDialog.findViewById(R.id.tv3);

                    textView.setText(employeeName);
                    textView2.setText(receiveDate);
                    textView3.setText(amountPaid);

                    alertDialog.show();

                    ImageView imageView = alertDialog.findViewById(R.id.imageView);
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            alertDialog.dismiss();
                        }
                    });

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