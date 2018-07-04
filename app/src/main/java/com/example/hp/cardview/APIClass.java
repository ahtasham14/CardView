package com.example.hp.cardview;

public class APIClass {

    public static String ROOT_URL = "http://ec2-18-222-165-11.us-east-2.compute.amazonaws.com:3002";
    public static String add_record = ROOT_URL+"/api/v1/employee/add_employee";
    public static String show_record = ROOT_URL+"/view";
    public static String show_specific_record = ROOT_URL+"/viewID";

    // Projects API Link
    public static String ProjectsShowRecord = ROOT_URL+"/api/v1/projects/showData";
    public static String byID = ROOT_URL+"/api/v1/projects/byID";

}
