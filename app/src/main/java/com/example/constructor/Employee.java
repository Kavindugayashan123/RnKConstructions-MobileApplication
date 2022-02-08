package com.example.constructor;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Time;

public class Employee implements Parcelable {
    private int E_ID;
    private float E_SALARY, E_FULL_SALARY, E_PAID_SALARY;
    private String E_TYPE, E_ROLE, E_NAME, E_IMAGE, E_ADDRESS, E_PHONE, E_NIC, E_GENDER, E_ADD_TIMESTAMP, E_UPDATE_TIMESTAMP, E_SITENAME, E_DATE;

    public Employee() {

    }

    public Employee(int e_ID, String e_TYPE, String e_ROLE, String e_NAME, String e_IMAGE, String e_ADDRESS, String e_PHONE, String e_NIC, String e_GENDER, String e_ADD_TIMESTAMP, String e_UPDATE_TIMESTAMP, String e_SITENAME, String e_DATE, Float e_SALARY, Float e_FULL_SALARY, Float e_PAID_SALARY) {
        E_ID = e_ID;
        E_TYPE = e_TYPE;
        E_ROLE = e_ROLE;
        E_NAME = e_NAME;
        E_IMAGE = e_IMAGE;
        E_ADDRESS = e_ADDRESS;
        E_PHONE = e_PHONE;
        E_NIC = e_NIC;
        E_GENDER = e_GENDER;
        E_ADD_TIMESTAMP = e_ADD_TIMESTAMP;
        E_UPDATE_TIMESTAMP = e_UPDATE_TIMESTAMP;
        E_SITENAME = e_SITENAME;
        E_DATE = e_DATE;
        E_SALARY = e_SALARY;
        E_FULL_SALARY = e_FULL_SALARY;
        E_PAID_SALARY = e_PAID_SALARY;

    }

    public Employee(String e_TYPE, String e_ROLE, String e_NAME, String e_IMAGE, String e_ADDRESS, String e_PHONE, String e_NIC, String e_GENDER, String e_ADD_TIMESTAMP, String e_UPDATE_TIMESTAMP,String e_SITENAME, String e_DATE, Float e_SALARY, Float e_FULL_SALARY, Float e_PAID_SALARY) {
        E_TYPE = e_TYPE;
        E_ROLE = e_ROLE;
        E_NAME = e_NAME;
        E_IMAGE = e_IMAGE;
        E_ADDRESS = e_ADDRESS;
        E_PHONE = e_PHONE;
        E_NIC = e_NIC;
        E_GENDER = e_GENDER;
        E_ADD_TIMESTAMP = e_ADD_TIMESTAMP;
        E_UPDATE_TIMESTAMP = e_UPDATE_TIMESTAMP;
        E_SITENAME = e_SITENAME;
        E_DATE = e_DATE;
        E_SALARY = e_SALARY;
        E_FULL_SALARY = e_FULL_SALARY;
        E_PAID_SALARY = e_PAID_SALARY;
    }

    protected Employee(Parcel in) {
        E_ID = in.readInt();
        E_TYPE = in.readString();
        E_ROLE = in.readString();
        E_NAME = in.readString();
        E_IMAGE = in.readString();
        E_ADDRESS = in.readString();
        E_PHONE = in.readString();
        E_NIC = in.readString();
        E_GENDER = in.readString();
        E_ADD_TIMESTAMP = in.readString();
        E_UPDATE_TIMESTAMP = in.readString();
        E_SITENAME = in.readString();
        E_DATE = in.readString();
        E_SALARY = in.readFloat();
        E_FULL_SALARY = in.readFloat();
        E_PAID_SALARY = in.readFloat();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public int getE_ID() {
        return E_ID;
    }

    public void setE_ID(int e_ID) {
        E_ID = e_ID;
    }

    public String getE_TYPE() {
        return E_TYPE;
    }

    public void setE_TYPE(String e_TYPE) {
        E_TYPE = e_TYPE;
    }

    public String getE_ROLE() {
        return E_ROLE;
    }

    public void setE_ROLE(String e_ROLE) {
        E_ROLE = e_ROLE;
    }

    public String getE_NAME() {
        return E_NAME;
    }

    public void setE_NAME(String e_NAME) {
        E_NAME = e_NAME;
    }

    public String getE_IMAGE() {
        return E_IMAGE;
    }

    public void setE_IMAGE(String e_IMAGE) {
        E_IMAGE = e_IMAGE;
    }

    public String getE_ADDRESS() {
        return E_ADDRESS;
    }

    public void setE_ADDRESS(String e_ADDRESS) {
        E_ADDRESS = e_ADDRESS;
    }

    public String getE_PHONE() {
        return E_PHONE;
    }

    public void setE_PHONE(String e_PHONE) {
        E_PHONE = e_PHONE;
    }

    public String getE_NIC() {
        return E_NIC;
    }

    public void setE_NIC(String e_NIC) {
        E_NIC = e_NIC;
    }

    public String getE_GENDER() {
        return E_GENDER;
    }

    public void setE_GENDER(String e_GENDER) {
        E_GENDER = e_GENDER;
    }

    public String getE_ADD_TIMESTAMP() {
        return E_ADD_TIMESTAMP;
    }

    public void setE_ADD_TIMESTAMP(String e_ADD_TIMESTAMP) {
        E_ADD_TIMESTAMP = e_ADD_TIMESTAMP;
    }

    public String getE_UPDATE_TIMESTAMP() {
        return E_UPDATE_TIMESTAMP;
    }

    public void setE_UPDATE_TIMESTAMP(String e_UPDATE_TIMESTAMP) {
        E_UPDATE_TIMESTAMP = e_UPDATE_TIMESTAMP;
    }

    public float getE_SALARY() {
        return E_SALARY;
    }

    public void setE_SALARY(float e_SALARY) {
        E_SALARY = e_SALARY;
    }

    public float getE_FULL_SALARY() {
        return E_FULL_SALARY;
    }

    public void setE_FULL_SALARY(float e_FULL_SALARY) {
        E_FULL_SALARY = e_FULL_SALARY;
    }

    public float getE_PAID_SALARY() {
        return E_PAID_SALARY;
    }

    public void setE_PAID_SALARY(float e_PAID_SALARY) {
        E_PAID_SALARY = e_PAID_SALARY;
    }

    public String getE_SITENAME() {
        return E_SITENAME;
    }

    public void setE_SITENAME(String e_SITENAME) {
        E_SITENAME = e_SITENAME;
    }

    public String getE_DATE() {
        return E_DATE;
    }

    public void setE_DATE(String e_DATE) {
        E_DATE = e_DATE;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(E_ID);
        dest.writeString(E_TYPE);
        dest.writeString(E_ROLE);
        dest.writeString(E_NAME);
        dest.writeString(E_IMAGE);
        dest.writeString(E_ADDRESS);
        dest.writeString(E_PHONE);
        dest.writeString(E_NIC);
        dest.writeString(E_GENDER);
        dest.writeString(E_ADD_TIMESTAMP);
        dest.writeString(E_UPDATE_TIMESTAMP);
        dest.writeString(E_SITENAME);
        dest.writeString(E_DATE);
        dest.writeFloat(E_SALARY);
        dest.writeFloat(E_FULL_SALARY);
        dest.writeFloat(E_PAID_SALARY);

    }
}
