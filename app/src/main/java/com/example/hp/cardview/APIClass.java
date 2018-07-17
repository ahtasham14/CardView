package com.example.hp.cardview;

import android.provider.DocumentsContract;

import com.android.volley.toolbox.StringRequest;

public class APIClass {

    public static String ROOT_URL = "http://ec2-18-222-165-11.us-east-2.compute.amazonaws.com:3000";
    public static String add_record = ROOT_URL+"/api/v1/employee/add_employee";
    public static String show_record = ROOT_URL+"/view";
    public static String show_specific_record = ROOT_URL+"/viewID";

    // Projects API Link
    public static String addProject = ROOT_URL+"/api/v1/projects/addProject";
    public static String ProjectsShowRecord = ROOT_URL+"/api/v1/projects/showData";
    public static String byID = ROOT_URL+"/api/v1/projects/byID";

    //Assets API

    public static String AssetsShowRecord = ROOT_URL + "/api/v1/assests/showData";
    public static String AssetsAddRecord = ROOT_URL+"/api/v1/assets/addAssets";

    // Credentials API

    public static String CredentialsShowRecord = ROOT_URL+ "/api/v1/credentials/showData";
    public static String AddCredentials = ROOT_URL+"/api/v1/credentials/addCredentials";

    public static String AddEmployeePaymet = ROOT_URL+"/api/v1/employeePayment/addEmployeePayment";


    public static String PaymentEmployees = ROOT_URL+"/api/v1/EmployessPayment/showData";

    public static String ClientPaymentRecord = ROOT_URL+ "/api/v1/ClientPayment/showData";
    public static String ClientPaymentADD = ROOT_URL+"/api/v1/clientPayment/addclientPayment";

}
