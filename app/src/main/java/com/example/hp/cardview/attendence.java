package com.example.hp.cardview;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import mehdi.sakout.fancybuttons.FancyButton;

public class attendence extends AppCompatActivity {

    TextView textView2;
    String ImageName = "";

    ImageView image1;
    ImageView image2;
    ImageView image3;

    boolean send = true;

    MaterialSpinner spinner;
    MaterialSpinner spinner2;
    MaterialSpinner spinner3;

    String spin1 = "";
    String spin2 = "";

    String spin3 = "";
    String currentDateTimeString;

    FancyButton fancyButton;
    FancyButton fancyButton2;

    TextView latehourss;
    TextView absentss;
    TextView halfdays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);

        // Date

        textView2 = findViewById(R.id.textView2);
        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        textView2.setText(currentDateTimeString);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);


        latehourss = findViewById(R.id.latehourss);
        absentss = findViewById(R.id.absentss);
        halfdays = findViewById(R.id.halfdays);

        fancyButton = findViewById(R.id.addattendence);
        fancyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addattendence();

            }
        });

        fancyButton2 = findViewById(R.id.showattendence);
        fancyButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    showAttendanceHOURS();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    CheckAbsent();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    HalfDays();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageName = "1";

                image2.setEnabled(false);
                image3.setEnabled(false);
                if (send) {
                    image1.setImageResource(R.drawable.oneblue);
                    send = false;
                } else {
                    image1.setImageResource(R.drawable.one);
                    send = true;
                }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageName = "2";

                image1.setEnabled(false);
                image3.setEnabled(false);
                if (send) {
                    image2.setImageResource(R.drawable.twoblue);
                    send = false;
                } else {
                    image2.setImageResource(R.drawable.two);
                    send = true;
                }
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageName = "3";
                image1.setEnabled(false);
                image2.setEnabled(false);
                if (send) {
                    image3.setImageResource(R.drawable.three_blue);
                    send = false;
                } else {
                    image3.setImageResource(R.drawable.three);
                    send = true;
                }
            }
        });

        spinner = findViewById(R.id.spinner);
        spinner =  findViewById(R.id.spinner);
        spinner.setItems("Ahtasham Ul Hassan", "Hashim Shafiq", "Muneeb Jan", "Muttayab Usama", "Kaleem Ur Rehman", "Mumtaz Hussain");
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                 spin1 = spinner.getText().toString();
            }
        });



        spinner2 = findViewById(R.id.spinner2);
        spinner2.setItems("Present", "Absent", "Half-Day", "Present-Late");
        spinner2.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                spin2 = spinner2.getText().toString();
            }
        });

        spinner3 = findViewById(R.id.spinner3);
        spinner3.setItems("Ahtasham Ul Hassan", "Hashim Shafiq", "Muneeb Jan", "Muttayab Usama", "Kaleem Ur Rehman", "Mumtaz Hussain");
        spinner3.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {

                spin3 = spinner3.getText().toString();
            }
        });
    }

    public void addattendence() {

        String tag_string_req = "addAttendence";
        String url = APIClass.addAttendence + "?name=" +spin1+ "&late_time=" + ImageName + "&date=" +currentDateTimeString+ "&attendence=" + spin2 +"";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.contains("200")) {
                    Toast.makeText(getApplicationContext(), "Data Added Succesful in Attendence", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to Add Data", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MyApplication.getInstance().addToRequestQueue(stringRequest, tag_string_req);

    }

    public void showAttendanceHOURS() throws UnsupportedEncodingException {

        String url = APIClass.lateHours+"?name="+URLEncoder.encode(spin3, "utf-8") ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);
                    JSONObject product = array.getJSONObject(0);

                    int latehours = product.getInt("LateHours");
                    latehourss.setText(latehours+"");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void CheckAbsent() throws UnsupportedEncodingException {

        String url = APIClass.checkAbsent+"?name="+URLEncoder.encode(spin3, "utf-8") ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);
                    JSONObject product = array.getJSONObject(0);

                    int absent = product.getInt("absent");
                    absentss.setText(absent+"");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public void HalfDays() throws UnsupportedEncodingException {

        String url = APIClass.halff+"?name="+URLEncoder.encode(spin3, "utf-8") ;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);
                    JSONObject product = array.getJSONObject(0);

                    int halfDays = product.getInt("halfdays");
                    halfdays.setText(halfDays+"");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}