package com.example.hp.cardview;

import android.provider.DocumentsContract;
import com.android.volley.toolbox.StringRequest;

public class APIClass {

    public static String ROOT_URL = "http://ec2-18-222-165-11.us-east-2.compute.amazonaws.com:3007";

    // Employee Class API's

    public static String add_record = ROOT_URL+"/api/v1/employee/add_employee";
    public static String show_record = ROOT_URL+"/view";
    public static String show_specific_record = ROOT_URL+"/viewID";

    public static String shift_ongoingTOcompleted = ROOT_URL+"/api/v1/Project/update_project";
    public static String edit_record = ROOT_URL+"/api/v1/Employee/EditEmployee";
    public static String delete_employee = ROOT_URL+"/api/v1/Employee/DeleteEmployee";

    public static String signup = ROOT_URL+"/api/v1/Bussiness_User/signup";
    public static String  sigin = ROOT_URL+"/api/v1/Bussiness_user/login";


    // Projects API Link
    public static String addProject = ROOT_URL+"/api/v1/projects/addProject";
    public static String ProjectsShowRecord = ROOT_URL+"/api/v1/projects/showData";
    public static String byID = ROOT_URL+"/api/v1/projects/byID";

    //Assets API
    public static String AssetsShowRecord = ROOT_URL+"/api/v1/assests/showData";
    public static String AssetsAddRecord = ROOT_URL+"/api/v1/assets/addAssets";


    // Credentials API
    public static String CredentialsShowRecord = ROOT_URL+"/api/v1/credentials/showData";
    public static String AddCredentials = ROOT_URL+"/api/v1/credentials/addCredentials";

    // By ID
    public static String SearchByID = ROOT_URL+"/api/v1/credentials/byID";

    public static String AddEmployeePaymet = ROOT_URL+"/api/v1/employeePayment/addEmployeePayment";

    public static String PaymentEmployees = ROOT_URL+"/api/v1/EmployessPayment/showData";

    public static String ClientPaymentRecord = ROOT_URL+ "/api/v1/ClientPayment/showData";
    public static String ClientPaymentADD = ROOT_URL+"/api/v1/clientPayment/addclientPayment";

    // Note
    public static String AddNote = ROOT_URL+"/api/v1/notes/addNotes";
    public static String showNotes = ROOT_URL+"/api/v1/notes/showNotes";

    public static String AssetByID = ROOT_URL+"/api/v1/assets/byID";

    public static String ClientPaymentByID = ROOT_URL+"/api/v1/clientPayment/byID";
    public static String EmployeePaymentByID = ROOT_URL+"/api/v1/employeePayment/byID";

    public static String NoteBYID = ROOT_URL+"/api/v1/Note/byID";

    public static String addAttendence = ROOT_URL+"/api/v1/attendence/addattendence";

    public static String checkAbsent = ROOT_URL+"/api/v1/attendance/absent";

    public  static  String lateHours = ROOT_URL+"/api/v1/attendance/lateHours";

    public static String halff = ROOT_URL +"/api/v1/attendance/halfday";


}
