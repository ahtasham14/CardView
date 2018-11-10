package com.example.hp.cardview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Notes extends AppCompatActivity {

    ArrayList <String> arrayList;
    ArrayAdapter arrayAdapter;
    ListView listView;
    Button button;

    Dialog alertDialog;
    ArrayList forID;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        alertDialog = new Dialog(this);

        arrayList = new ArrayList<>();
        forID = new ArrayList<>();

        listView = findViewById(R.id.list);
        button = findViewById(R.id.assetButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), addNotes.class);
                startActivity(intent);
            }
        });

        loadNotes();
    }

    private void loadNotes() {

        String url = APIClass.showNotes;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {

                        JSONObject product = array.getJSONObject(i);

                        arrayList.add(product.getString("noteTitle"));
                        forID.add(product.getInt("id"));
                    }
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override

                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            int id = (int) forID.get(i);
                            NoteBYID(id);

                        }
                    });

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

    private void NoteBYID(int id) {

        String url = APIClass.NoteBYID+"?id="+id;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);
                    JSONObject product = array.getJSONObject(0);

                    String employeeName = product.getString("noteTitle");
                    String note = product.getString("note");
                    String date = product.getString("date");

                    alertDialog.setContentView(R.layout.custompopup);

                    TextView textView =   alertDialog.findViewById(R.id.tv);
                    TextView textView1 = alertDialog.findViewById(R.id.date);
                    TextView textView2 =   alertDialog.findViewById(R.id.tv2);

                    textView.setText(employeeName);
                    textView2.setText(note);
                    textView1.setText(date);

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