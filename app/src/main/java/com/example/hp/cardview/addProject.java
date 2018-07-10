package com.example.hp.cardview;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
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

import java.sql.Array;
import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;

public class addProject extends AppCompatActivity {
    Spinner spinner;
    ArrayList<String> arrayList;

    Spinner spinner2;
    ArrayList<String> arrayList2;

    Spinner Spinner3;
    ArrayList<String> arrayList3;

    TextView textView;
    TextView textView2;

    EditText ed1;
    EditText ed2;

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;
    EditText editText5;
    EditText editText6;
    EditText editText7;


    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        spinner = findViewById(R.id.spinner);
        Calendar calender;
        arrayList = new ArrayList<>();
        arrayList.add("Fiverr");
        arrayList.add("Upwork");
        arrayList.add("FreeLancer");
        arrayList.add("TopTell");

        button = findViewById(R.id.button);

        ArrayAdapter spinnerAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList);
        spinner.setAdapter(spinnerAdapter);

        spinner2 = findViewById(R.id.spinner2);

        arrayList2 = new ArrayList<>();
        arrayList2.add("Usama");
        arrayList2.add("Muneeb");
        arrayList2.add("Hashim");
        arrayList2.add("Kaleem");

        ArrayAdapter spinnerAdapter2 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList2);
        spinner2.setAdapter(spinnerAdapter2);

        Spinner3 = findViewById(R.id.spinner3);
        arrayList3 = new ArrayList<>();

        arrayList3.add("ongoing");
        arrayList3.add("completed");
        arrayList3.add("queue");


        ArrayAdapter spinnerAdapter3 = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, arrayList3);
        Spinner3.setAdapter(spinnerAdapter3);


        calender = Calendar.getInstance();
        final int Day = calender.get(Calendar.DAY_OF_MONTH);
        final int month = calender.get(Calendar.MONTH);
        final int year = calender.get(Calendar.YEAR);


        ed1 = findViewById(R.id.editText3);
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(addProject.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                ed1.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, year, month, Day);
                datePickerDialog.show();
            }
        });


        ed2 = findViewById(R.id.editText4);
        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(addProject.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                ed2.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, year, month, Day);
                datePickerDialog.show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editText1 = findViewById(R.id.editText1);
                editText2 = findViewById(R.id.editText2);
                editText3 = findViewById(R.id.editText3);
                editText4 = findViewById(R.id.editText4);
                editText5 = findViewById(R.id.editText5);
                editText6 = findViewById(R.id.editText6);
                editText7 = findViewById(R.id.editText7);

                String projectName = editText1.getText().toString();
                String clientName = editText2.getText().toString();
                String startingDate = editText3.getText().toString();
                String endingDate = editText4.getText().toString();
                String cost = editText5.getText().toString();
                String fund = editText6.getText().toString();
                String codeLink = editText7.getText().toString();

                String platform = spinner.getSelectedItem().toString();
                String team = spinner2.getSelectedItem().toString();
                String projectType = Spinner3.getSelectedItem().toString();

                String tag_string_req = "add_project";
                String url = APIClass.addProject + "?name=" + projectName + "&platform=" + platform + "&team=" + team + "&starting=" + startingDate + "&ending=" + endingDate + "&funds=" + fund + "&cost=" + cost + "&code=" + codeLink + "&client=" + clientName + "&ptype=" + projectType;


                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.contains("200")) {
                            Toast.makeText(getApplicationContext(), "Data Added Succesful in Projects", Toast.LENGTH_SHORT).show();
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
        });
    }
}
