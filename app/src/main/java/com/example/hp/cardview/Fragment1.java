package com.example.hp.cardview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Fragment1 extends Fragment {

    ListView listView;
    ArrayList<String> projectList;
    ArrayAdapter arrayAdapter;
    project project;
    ArrayList<Integer>IDprojectList;

    public Fragment1() {
        projectList = new ArrayList<>();
        IDprojectList = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        listView = view.findViewById(R.id.listView);
        showQueue();
        return  view;
    }

    public void showQueue(){

            projectList.clear();
            IDprojectList.clear();
            String url = APIClass.ProjectsShowRecord+"?project_type="+"ongoing";
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
