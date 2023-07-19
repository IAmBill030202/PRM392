package com.example.practicalexam.Model;

public class Contact {
    private int empId;
    private String empName;
    private String empEmail;
    private String empAddress;
    private String notes;

    public Contact(int empId, String empName, String empEmail, String empAddress, String notes) {
        this.empId = empId;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empAddress = empAddress;
        this.notes = notes;
    }

    // Getters and Setters
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
