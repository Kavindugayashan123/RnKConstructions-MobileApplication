package com.example.constructor;

import android.os.Parcel;
import android.os.Parcelable;

public class Stoke implements Parcelable {


    private int  S_ID;
    private  String  S_NAME, S_QTY, S_IMAGE, S_ADD_TIMESTAMP, S_UPDATE_TIMESTAMP, S_DATE, S_SALARY, S_SITENAME;

    public Stoke(){

    }

    public Stoke(int s_ID, String s_NAME, String s_QTY, String s_IMAGE, String s_ADD_TIMESTAMP, String s_UPDATE_TIMESTAMP, String s_DATE, String s_SALARY, String s_SITENAME) {
        S_ID = s_ID;
        S_NAME = s_NAME;
        S_QTY = s_QTY;
        S_IMAGE = s_IMAGE;
        S_ADD_TIMESTAMP = s_ADD_TIMESTAMP;
        S_UPDATE_TIMESTAMP = s_UPDATE_TIMESTAMP;
        S_DATE = s_DATE;
        S_SALARY = s_SALARY;
        S_SITENAME = s_SITENAME;
    }

    public Stoke(String s_NAME, String s_QTY, String s_IMAGE, String s_ADD_TIMESTAMP, String s_UPDATE_TIMESTAMP, String s_DATE, String s_SALARY, String s_SITENAME) {
        S_NAME = s_NAME;
        S_QTY = s_QTY;
        S_IMAGE = s_IMAGE;
        S_ADD_TIMESTAMP = s_ADD_TIMESTAMP;
        S_UPDATE_TIMESTAMP = s_UPDATE_TIMESTAMP;
        S_DATE = s_DATE;
        S_SALARY = s_SALARY;
        S_SITENAME = s_SITENAME;
    }

    protected Stoke(Parcel in) {
        S_ID = in.readInt();
        S_NAME = in.readString();
        S_QTY = in.readString();
        S_IMAGE = in.readString();
        S_ADD_TIMESTAMP = in.readString();
        S_UPDATE_TIMESTAMP = in.readString();
        S_DATE = in.readString();
        S_SALARY = in.readString();
        S_SITENAME = in.readString();
    }

    public static final Creator<Stoke> CREATOR = new Creator<Stoke>() {
        @Override
        public Stoke createFromParcel(Parcel in) {
            return new Stoke(in);
        }

        @Override
        public Stoke[] newArray(int size) {
            return new Stoke[size];
        }
    };

    public int getS_ID() {
        return S_ID;
    }

    public void setS_ID(int s_ID) {
        S_ID = s_ID;
    }

    public String getS_NAME() {
        return S_NAME;
    }

    public void setS_NAME(String s_NAME) {
        S_NAME = s_NAME;
    }

    public String getS_QTY() {
        return S_QTY;
    }

    public void setS_QTY(String s_QTY) {
        S_QTY = s_QTY;
    }

    public String getS_IMAGE() {
        return S_IMAGE;
    }

    public void setS_IMAGE(String s_IMAGE) {
        S_IMAGE = s_IMAGE;
    }

    public String getS_ADD_TIMESTAMP() {
        return S_ADD_TIMESTAMP;
    }

    public void setS_ADD_TIMESTAMP(String s_ADD_TIMESTAMP) {
        S_ADD_TIMESTAMP = s_ADD_TIMESTAMP;
    }

    public String getS_UPDATE_TIMESTAMP() {
        return S_UPDATE_TIMESTAMP;
    }

    public void setS_UPDATE_TIMESTAMP(String s_UPDATE_TIMESTAMP) {
        S_UPDATE_TIMESTAMP = s_UPDATE_TIMESTAMP;
    }

    public String getS_DATE() {
        return S_DATE;
    }

    public void setS_DATE(String s_DATE) {
        S_DATE = s_DATE;
    }

    public String getS_SALARY() {
        return S_SALARY;
    }

    public void setS_SALARY(String s_SALARY) {
        S_SALARY = s_SALARY;
    }

    public String getS_SITENAME() {
        return S_SITENAME;
    }

    public void setS_SITENAME(String s_SITENAME) {
        S_SITENAME = s_SITENAME;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(S_ID);
        dest.writeString(S_NAME);
        dest.writeString(S_QTY);
        dest.writeString(S_IMAGE);
        dest.writeString(S_ADD_TIMESTAMP);
        dest.writeString(S_UPDATE_TIMESTAMP);
        dest.writeString(S_DATE);
        dest.writeString(S_SALARY);
        dest.writeString(S_SITENAME);
    }
}
