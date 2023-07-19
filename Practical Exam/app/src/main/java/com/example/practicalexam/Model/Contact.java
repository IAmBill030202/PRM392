package com.example.practicalexam.Model;
import android.os.Parcel;
import android.os.Parcelable;
public class Contact implements Parcelable {
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
    protected Contact(Parcel in) {
        empId = in.readInt();
        empName = in.readString();
        empEmail = in.readString();
        empAddress = in.readString();
        notes = in.readString();
    }
    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(empId);
        dest.writeString(empName);
        dest.writeString(empEmail);
        dest.writeString(empAddress);
        dest.writeString(notes);
    }
    @Override
    public int describeContents() {
        return 0;
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
