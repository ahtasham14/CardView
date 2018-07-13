package com.example.hp.cardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                addAssets();
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

                        //traversing through all the object
                        JSONObject product = array.getJSONObject(0);

                        //   employee = new Employee();
                        arrayList.add(product.getString("Item"));
                        String buyDate = product.getString("buyDate");
                        String cost = product.getString("cost");


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

    private void addAssets() {
        //   progressBar.setVisibility(View.VISIBLE);
        String EditText1 = ed1.getText().toString().trim();
        String EditText2 = ed2.getText().toString().trim();
        String EditText3 = ed3.getText().toString().trim();

        String tag_string_req = "add_assets";
        String url = APIClass.AssetsAddRecord+ "?item="+EditText1+"&buydate="+EditText2+"&cost="+EditText3+"";
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