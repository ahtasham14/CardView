package com.example.hp.cardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class Assest extends AppCompatActivity {

    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    ListView listView;
    Button button;
    EditText ed1;
    EditText ed2;
    EditText ed3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assest);

        arrayList = new ArrayList<>();
        listView = findViewById(R.id.list);

        button = findViewById(R.id.assetButton);
        loadAssets();



        button = findViewById(R.id.assetButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), addAssets.class);
                startActivity(intent);
                finish();
            }
        });
    }
        private void loadAssets() {

            String url = APIClass.AssetsShowRecord;
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
                            arrayList.add(product.getString("Item"));

                            String buyDate = product.getString("buyDate");
                            String cost = product.getString("cost");


                        }

                        String zero = (String) arrayList.get(0);
                        System.out.println(zero);

                        arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,arrayList);
                        listView.setAdapter(arrayAdapter);

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