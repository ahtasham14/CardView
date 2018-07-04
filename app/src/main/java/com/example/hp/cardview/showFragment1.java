package com.example.hp.cardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

public class showFragment1 extends AppCompatActivity {
    ArrayList<String> arrayList;
    ArrayList<Integer> idList;
    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    TextView textView7;
    TextView textView8;
    TextView textView9;
    project project;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_fragment1);

        Intent intent = getIntent();
        id = intent.getIntExtra("ID",id);
        arrayList = intent.getStringArrayListExtra("projectList");
        idList = intent.getIntegerArrayListExtra("projectList2");
        loadEmployees(id);
    }
        private void loadEmployees(int id) {

            String url = APIClass.byID+"?id="+id;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        // First Access the Array and then Access the Objects
                        JSONArray array = new JSONArray(response);

                        //traversing through all the object
                        JSONObject product = array.getJSONObject(0);

                        String name = product.getString("name");
                        String platform = product.getString("platform");
                        String team = product.getString("team");
                        String starting_date= product.getString("starting_date");
                        String ending_Date= product.getString("ending_date");
                        String fund = product.getString("funds");
                        String cost = product.getString("cost");
                        String code_link = product.getString("code_link");
                        String client = product.getString("client");
                        String project_type = product.getString("project_type");

                        textView = (TextView)findViewById(R.id.platform);
                        textView2 = (TextView)findViewById(R.id.projectName);
                        textView3 = (TextView)findViewById(R.id.clientname);
                        textView4 = (TextView)findViewById(R.id.funds);
                        textView5 = (TextView)findViewById(R.id.cost);
                        textView6 = (TextView)findViewById(R.id.edate);
                        textView7 = (TextView)findViewById(R.id.sdate);
                        textView8 = (TextView)findViewById(R.id.team);
                        textView9 = (TextView)findViewById(R.id.code_link);


                        textView.setText(platform);
                        textView2.setText(name);
                        textView3.setText(client);
                        textView4.setText(fund);
                        textView5.setText(cost);
                        textView6.setText(ending_Date);
                        textView7.setText(starting_date);
                        textView8.setText(team);
                        textView9.setText(code_link);


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