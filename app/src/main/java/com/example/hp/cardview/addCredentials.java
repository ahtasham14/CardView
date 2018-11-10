package com.example.hp.cardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class addCredentials extends AppCompatActivity {

    EditText ed1;
    EditText ed2;
    EditText ed3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credentials);

        button = findViewById(R.id.Credentials);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCredentials();
            }
        });
    }
    private void addCredentials() {
        //   progressBar.setVisibility(View.VISIBLE);

        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);
        ed3 = findViewById(R.id.editText3);

        final String EditText1 = ed1.getText().toString().trim();
        final String EditText2 = ed2.getText().toString().trim();
        final String EditText3 = ed3.getText().toString().trim();

        String tag_string_req = "add_credentials";

        String url = APIClass.AddCredentials+"?username="+EditText1+"&password="+EditText2+"&account="+EditText3+"";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(EditText1.isEmpty() || EditText2.isEmpty() || EditText3.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Make sure all fields are filled", Toast.LENGTH_SHORT).show();
                }else {
                    // progressBar.setVisibility(View.GONE);
                    if (response.contains("200")) {
                        Toast.makeText(getApplicationContext(), "Data Added Succesful in Assets", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to Add Data", Toast.LENGTH_SHORT).show();
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
}