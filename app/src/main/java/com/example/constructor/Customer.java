package com.example.constructor;

import android.os.Parcel;
import android.os.Parcelable;

public class Customer implements Parcelable{
          private int  C_ID ;
          private String C_NAM,C_ADDRESS,C_PHONE, C_IMAGE, C_ADD_TIMESTAMP, C_UPDATE_TIMESTAMP, C_NIC, C_DATE;

          public Customer(){

          }

    public Customer(int c_ID, String c_NAM, String c_ADDRESS, String c_PHONE, String c_IMAGE, String c_ADD_TIMESTAMP, String c_UPDATE_TIMESTAMP, String c_NIC, String c_DATE) {
        C_ID = c_ID;
        C_NAM = c_NAM;
        C_ADDRESS = c_ADDRESS;
        C_PHONE = c_PHONE;
        C_IMAGE = c_IMAGE;
        C_ADD_TIMESTAMP = c_ADD_TIMESTAMP;
        C_UPDATE_TIMESTAMP = c_UPDATE_TIMESTAMP;
        C_NIC = c_NIC;
        C_DATE = c_DATE;
    }

    public Customer(String c_NAM, String c_ADDRESS, String c_PHONE, String c_IMAGE, String c_ADD_TIMESTAMP, String c_UPDATE_TIMESTAMP, String c_NIC, String c_DATE) {
        C_NAM = c_NAM;
        C_ADDRESS = c_ADDRESS;
        C_PHONE = c_PHONE;
        C_IMAGE = c_IMAGE;
        C_ADD_TIMESTAMP = c_ADD_TIMESTAMP;
        C_UPDATE_TIMESTAMP = c_UPDATE_TIMESTAMP;
        C_NIC = c_NIC;
        C_DATE = c_DATE;
    }

    protected Customer(Parcel in) {
        C_ID = in.readInt();
        C_NAM = in.readString();
        C_ADDRESS = in.readString();
        C_PHONE = in.readString();
        C_IMAGE = in.readString();
        C_ADD_TIMESTAMP = in.readString();
        C_UPDATE_TIMESTAMP = in.readString();
        C_NIC = in.readString();
        C_DATE = in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public int getC_ID() {
        return C_ID;
    }

    public void setC_ID(int c_ID) {
        C_ID = c_ID;
    }

    public String getC_NAM() {
        return C_NAM;
    }

    public void setC_NAM(String c_NAM) {
        C_NAM = c_NAM;
    }

    public String getC_ADDRESS() {
        return C_ADDRESS;
    }

    public void setC_ADDRESS(String c_ADDRESS) {
        C_ADDRESS = c_ADDRESS;
    }

    public String getC_PHONE() {
        return C_PHONE;
    }

    public void setC_PHONE(String c_PHONE) {
        C_PHONE = c_PHONE;
    }

    public String getC_IMAGE() {
        return C_IMAGE;
    }

    public void setC_IMAGE(String c_IMAGE) {
        C_IMAGE = c_IMAGE;
    }

    public String getC_ADD_TIMESTAMP() {
        return C_ADD_TIMESTAMP;
    }

    public void setC_ADD_TIMESTAMP(String c_ADD_TIMESTAMP) {
        C_ADD_TIMESTAMP = c_ADD_TIMESTAMP;
    }

    public String getC_UPDATE_TIMESTAMP() {
        return C_UPDATE_TIMESTAMP;
    }

    public void setC_UPDATE_TIMESTAMP(String c_UPDATE_TIMESTAMP) {
        C_UPDATE_TIMESTAMP = c_UPDATE_TIMESTAMP;
    }

    public String getC_NIC() {
        return C_NIC;
    }

    public void setC_NIC(String c_NIC) {
        C_NIC = c_NIC;
    }

    public String getC_DATE() {
        return C_DATE;
    }

    public void setC_DATE(String c_DATE) {
        C_DATE = c_DATE;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(C_ID);
        dest.writeString(C_NAM);
        dest.writeString(C_ADDRESS);
        dest.writeString(C_PHONE);
        dest.writeString(C_IMAGE);
        dest.writeString(C_ADD_TIMESTAMP);
        dest.writeString(C_UPDATE_TIMESTAMP);
        dest.writeString(C_NIC);
        dest.writeString(C_DATE);
    }
}
