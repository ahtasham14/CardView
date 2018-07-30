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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Credentials extends AppCompatActivity {
    ListView ListView;
    Button button;
    ArrayList ArrayList;
    ArrayAdapter arrayAdapter;
    Dialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);

        ArrayList = new ArrayList<>();
        ListView = findViewById(R.id.listC);
        button = findViewById(R.id.assetButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), addCredentials.class);
                startActivity(intent);
                finish();
            }
        });

        loadCredentials();

    }

        private void loadCredentials() {

            String url = APIClass.CredentialsShowRecord;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {


                            // First Access the Array and then Access the Objects
                            JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {
                            //traversing through all the object
                            JSONObject product = array.getJSONObject(i);

                            //   employee = new Employee();
                            ArrayList.add(product.getString("account"));
                            String Password = product.getString("password");
                            String Account = product.getString("username");

                            ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    alertDialog.setContentView(R.layout.custompopup);
                                    alertDialog.show();

                                    ImageView textView = alertDialog.findViewById(R.id.imageView);
                                    textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            alertDialog.dismiss();
                                        }
                                    });
                                }
                            });
                        }


                        String zero = (String) ArrayList.get(0);
                        System.out.println(zero);

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

            //adding our stringrequest to queue
            Volley.newRequestQueue(this).add(stringRequest);
        }
    }