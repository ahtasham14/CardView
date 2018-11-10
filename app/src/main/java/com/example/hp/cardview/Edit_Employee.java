package com.example.hp.cardview;

import android.app.job.JobInfo;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import javax.sql.StatementEvent;

public class Edit_Employee extends AppCompatActivity {

    Employee employee;
    TextView tv;
    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__employee);

        Intent intent = getIntent();
        final int ID = intent.getExtras().getInt("Id");
        getRecordby_ID(ID);

        tv = findViewById(R.id.editText1);
        tv1 = findViewById(R.id.editText2);
        tv2 = findViewById(R.id.editText3);
        tv3 = findViewById(R.id.editText4);
        tv4 = findViewById(R.id.editText5);
        tv5 = findViewById(R.id.editText6);
        tv6 = findViewById(R.id.editText7);
        tv7 = findViewById(R.id.editText8);

        button = findViewById(R.id.add_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String tag_string_req = "add_employees";

                final String Name = tv.getText().toString().trim();
                final String Title = tv1.getText().toString().trim();
                final String Total_Paid = tv2.getText().toString().trim();
                final String Salary = tv3.getText().toString().trim();
                final String Email = tv4.getText().toString().trim();
                final String Joining = tv5.getText().toString().trim();
                final String Mobile = tv6.getText().toString().trim();
                final String Note = tv7.getText().toString().trim();

                String url = APIClass.edit_record + "?id="+ID+ "&name=" + Name + "&title=" + Title + "&total_paid=" + Total_Paid + "&salary=" + Salary + "&email=" + Email + "&starting_date="+Joining+"&mobilephone="+Mobile+"&note="+Note+"";
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // progressBar.setVisibility(View.GONE);

                        if (Name.isEmpty() || Title.isEmpty() || Total_Paid.isEmpty() || Salary.isEmpty() || Email.isEmpty() || Joining.isEmpty() || Mobile.isEmpty() || Note.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "Make sure all fields to be fields.", Toast.LENGTH_SHORT).show();
                        } else {
                            if (response.contains("200")) {
                                Toast.makeText(getApplicationContext(), "Data Added Succesful in Employees", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to Add Data in Employees", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // progressBar.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println(error.getMessage());
                    }
                });
                MyApplication.getInstance().addToRequestQueue(stringRequest, tag_string_req);
            }

        });

    }

        private void getRecordby_ID (int ID) {
            String url = APIClass.show_specific_record + "?id=" + ID;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONArray array = new JSONArray(response);
                        JSONObject product = array.getJSONObject(0);

                        String Name = product.getString("name");
                        String Title = product.getString("title");
                        String Total_Paid = product.getString("total_paid");
                        String Salary = product.getString("salary");
                        String Email = product.getString("email");
                        String Joining = product.getString("starting_date");
                        String Mobile = product.getString("mobilephone");
                        String Note = product.getString("note");

                        tv.setText(Name);
                        tv1.setText(Title);
                        tv2.setText(Total_Paid);
                        tv3.setText(Salary);
                        tv4.setText(Email);
                        tv5.setText(Joining);
                        tv6.setText(Mobile);
                        tv7.setText(Note);


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




