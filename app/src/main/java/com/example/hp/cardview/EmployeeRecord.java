package com.example.hp.cardview;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.BounceInterpolator;
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
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EmployeeRecord extends AppCompatActivity {

    ArrayList arrayList;
    ArrayList idList;
    ArrayAdapter arrayAdapter;
    SwipeMenuListView listView;
    Employee employee;
    Dialog alertDialog;

    Button button;
    Button button1;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee__record);

        alertDialog = new Dialog(this);
        listView = findViewById(R.id.listView);
        idList = new ArrayList();
        arrayList = new ArrayList();

        // Swipe Menue Creator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem editItem = new SwipeMenuItem(getApplicationContext());
                editItem.setWidth(150);
                editItem.setIcon(R.drawable.ic_edit);
                menu.addMenuItem(editItem);


                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setWidth(150);
                deleteItem.setIcon(R.drawable.delete_blue);
                menu.addMenuItem(deleteItem);
            }
        };

        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(final int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        int i = (int) idList.get(position);
                        edit(i);
                        break;
                    case 1:
                        int a = (int)idList.get(position);
                        delete(a);
                        break;
                }
                return false;
            }
        });

        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_RIGHT);
        listView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        listView.setCloseInterpolator(new BounceInterpolator());

        button = findViewById(R.id.add_Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), addEmployee.class);
                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent();
        arrayList = intent.getStringArrayListExtra("namesArray");
        idList = intent.getIntegerArrayListExtra("employeeArray");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int id = (int) idList.get(i);
                loadEmployees(id);
            }
        });
    }

    public void delete (final int a) {

                    alertDialog.setContentView(R.layout.delete);
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

                            String url = APIClass.delete_employee+"?id="+a;
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                     // arrayAdapter.remove(a);
                                    //   arrayList.remove(a);
                                   // arrayAdapter.notifyDataSetChanged();
                                    Toast.makeText(getApplicationContext(), "Employee Deleted", Toast.LENGTH_SHORT).show();

                                    alertDialog.dismiss();
                                  //  arrayList.remove(a);
                                }
                            },
                                    new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                        }
                                    });

                            Volley.newRequestQueue(getApplicationContext()).add(stringRequest);
                        }
                    });
    }

    public void edit(final int i){

        alertDialog.setContentView(R.layout.custom_edit);
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

                Intent intent = new Intent(getApplicationContext(), Edit_Employee.class);
                intent.putExtra("Id", i);
                startActivity(intent);

            }
        });
    }

        private void loadEmployees(int id) {

            String url = APIClass.show_specific_record+"?id="+id;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONArray array = new JSONArray(response);
                        JSONObject product = array.getJSONObject(0);

                        employee = new Employee();
                        employee.setId(product.getString("id"));
                        employee.setName(product.getString("name"));
                        employee.setTitle(product.getString("title"));
                        employee.setMobile(product.getString("mobilephone"));
                        employee.setEmail(product.getString("email"));
                        employee.setStartingDate(product.getString("starting_date"));
                        employee.setTotalPaid(product.getString("total_paid"));
                        employee.setSalary(product.getString("salary"));
                        employee.setNote(product.getString("note"));

                        System.out.println(employee.toString());

                        Intent intent = new Intent(getApplicationContext(), AllEmployeeInfo.class);
                        intent.putExtra("employeeData", employee);
                        startActivity(intent);

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