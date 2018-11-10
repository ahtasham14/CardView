package com.example.hp.cardview;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class Fragment3 extends Fragment {

    ListView listView;
    ArrayList<String> projectList;
    ArrayAdapter arrayAdapter;
    project project;
    ArrayList<Integer>IDprojectList;

    Dialog alertDialog;
    Button button1;
    Button button2;


    public Fragment3() {
        projectList = new ArrayList<>();
        IDprojectList = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        listView = view.findViewById(R.id.listView);
        alertDialog = new Dialog(getContext());
        showOngoing();

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {

                alertDialog.setContentView(R.layout.shift);
                alertDialog.show();
                button2 = alertDialog.findViewById(R.id.cancelButton);
                button1 = alertDialog.findViewById(R.id.deleteButton);

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int aht = IDprojectList.get(i);
                        shift_ongoingTOcompleted(aht);
                    }
                });

                return true;
            }
        });

        return  view;

    }

    public void shift_ongoingTOcompleted(int i){

        final String tag_string_req = "shift_project";

        String url = APIClass.shift_ongoingTOcompleted + "?id="+i+ "&type="+ "ongoing" ;
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                // progressBar.setVisibility(View.GONE);

                if (response.contains("200")) {
                    Toast.makeText(getContext(), "Data Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Failed to update Data", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(error.getMessage());
            }
        });
        MyApplication.getInstance().addToRequestQueue(stringRequest, tag_string_req);
    }

    public void showOngoing(){

            projectList.clear();
            IDprojectList.clear();
            String url = APIClass.ProjectsShowRecord+"?project_type="+"queue";
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        // First Access the Array and then Access the Objects
                        JSONArray array = new JSONArray(response);
                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            JSONObject product = array.getJSONObject(i);
                            // System.out.println(product.getString("name"));
                            projectList.add(product.getString("name"));
                            IDprojectList.add(product.getInt("id"));
                        }

                        arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,projectList);
                        listView.setAdapter(arrayAdapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                int id = IDprojectList.get(i);
                                Intent intent = new Intent(getContext(),showFragment1.class);
                                intent.putExtra("ID", id);
                                intent.putStringArrayListExtra("projectList", projectList);
                                intent.putIntegerArrayListExtra("projectList2",IDprojectList);
                                startActivity(intent);

                            }
                        });


                    } catch (Exception e) {
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
            Volley.newRequestQueue(getContext()).add(stringRequest);
        }
}