package com.example.hp.cardview;

import android.content.Intent;
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

public class signup extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        tv1 = findViewById(R.id.editText1);
        tv2 = findViewById(R.id.editText2);
        tv3 = findViewById(R.id.editText3);
        tv4 = findViewById(R.id.editText4);
        tv5 = findViewById(R.id.editText5);
        button = findViewById(R.id.buttonsign_up);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String Username = tv1.getText().toString().trim();
                final String Email = tv2.getText().toString().trim();
                final String Mobile = tv3.getText().toString().trim();
                final String Password = tv4.getText().toString().trim();
                final String Confirm = tv5.getText().toString().trim();

                final String tag_string_req = "signup";

                String url = APIClass.signup + "?username=" + Username + "&email=" + Email + "&mobile=" + Mobile + "&password=" + Password + "&cp=" + Confirm +"";
                final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        // progressBar.setVisibility(View.GONE);

                        if(!Password.equals(Confirm)){
                            Toast.makeText(getApplicationContext(), "Password not Matches", Toast.LENGTH_LONG).show();
                        }

                        if (Username.isEmpty() || Email.isEmpty() || Mobile.isEmpty() || Password.isEmpty() || Confirm.isEmpty()) {

                                Toast.makeText(getApplicationContext(), "Make sure all fields to be fields OR Password is not Matching.", Toast.LENGTH_SHORT).show();
                        }else {
                            if (response.contains("200")) {
                                Toast.makeText(getApplicationContext(), "Data Added Succesful in Employees", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent( getApplicationContext(), WelcomeScreen.class);
                                intent.putExtra("username", Username);
                                startActivity(intent);
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
}