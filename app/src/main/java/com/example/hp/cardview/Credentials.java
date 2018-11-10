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

public class Credentials extends AppCompatActivity {
    ListView ListView;
    Button button;
    ArrayList ArrayList;
    ArrayAdapter arrayAdapter;
    Dialog alertDialog;
    ArrayList forID;
//    ArrayList searchID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);

        alertDialog = new Dialog(this);

        ArrayList = new ArrayList<>();
        forID = new ArrayList<>();

        ListView = findViewById(R.id.listC);
        button = findViewById(R.id.assetButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), addCredentials.class);
                startActivity(intent);
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

                            JSONArray array = new JSONArray(response);
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject product = array.getJSONObject(i);

                            ArrayList.add(product.getString("account"));
                            forID.add(product.getInt("id"));

                        }
                            ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override

                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    int id = (int) forID.get(i);
                                    CredentailsBYID(id);
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

    private void CredentailsBYID(int id) {

        String url = APIClass.SearchByID+"?id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);

                    JSONObject product = array.getJSONObject(0);

                    String username = product.getString("username");
                    String password = product.getString("password");
                    String account =  product.getString("account");

                    alertDialog.setContentView(R.layout.custom_layout);

                    TextView textView =   alertDialog.findViewById(R.id.tv1);
                    TextView textView2 =  alertDialog.findViewById(R.id.tv2);
                    TextView textView3 =  alertDialog.findViewById(R.id.tv3);

                    textView.setText(username);
                    textView2.setText(password);
                    textView3.setText(account);

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