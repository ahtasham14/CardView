package com.example.hp.cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class ClientPayment extends AppCompatActivity {

    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    ListView listView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_payment);

        arrayList = new ArrayList<>();
        listView = findViewById(R.id.listClientPayment);
        loadClientPayment();
    }

    private void loadClientPayment() {

        String url = APIClass.ClientPaymentRecord;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                JSONArray jsonArray = new JSONArray(response);

                    if(jsonArray.length()>0) {


                        // First Access the Array and then Access the Objects


                        //traversing through all the object
                        JSONObject product = jsonArray.getJSONObject(0);

                        //   employee = new Employee();
                        arrayList.add(product.getString("clientName"));
                        //String buyDate = product.getString("buyDate");
                        // String cost = product.getString("cost");


                        String zero = (String) arrayList.get(0);
                        System.out.println(zero);

                        arrayAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
                        listView.setAdapter(arrayAdapter);
                    }else{
                        Toast.makeText(getApplicationContext(),"Could not get data from api",Toast.LENGTH_SHORT).show();
                    }

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
