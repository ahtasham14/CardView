package com.example.hp.cardview;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class addNotes extends AppCompatActivity {
    EditText ed1;
    EditText ed2;
    Button button;
    Dialog dialog;
    Dialog alertDialog;
    TextView textView;
    TextView textView2;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        button = findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotes();
            }
        });


    }

    private void addNotes() {
        //   progressBar.setVisibility(View.VISIBLE);

        ed1 = findViewById(R.id.editText1);
        ed2 = findViewById(R.id.editText2);

        String EditText1 = ed1.getText().toString().trim();
        String EditText2 = ed2.getText().toString().trim();

        String tag_string_req = "add_assets";

        String url = APIClass.AddNote + "?title=" + EditText1 + "&notes=" + EditText2;
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
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(error.getMessage());
            }
        });

        MyApplication.getInstance().addToRequestQueue(stringRequest, tag_string_req);

    }
}