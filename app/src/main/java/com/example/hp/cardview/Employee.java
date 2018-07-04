package com.example.hp.cardview;

import java.io.Serializable;

public class Employee implements Serializable {

    private String id;
    private String name;
    private String startingDate;
    private String totalPaid;
    private String salary;
    private String note;

    public Employee(String id, String name, String startingDate, String totalPaid, String salary, String note) {
        this.id = id;
        this.name = name;
        this.startingDate = startingDate;
        this.totalPaid = totalPaid;
        this.salary = salary;
        this.note = note;
    }

    public Employee(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(String totalPaid) {
        this.totalPaid = totalPaid;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", startingDate='" + startingDate + '\'' +
                ", totalPaid='" + totalPaid + '\'' +
                ", salary='" + salary + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
