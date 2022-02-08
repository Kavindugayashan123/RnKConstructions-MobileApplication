package com.example.constructor;

import android.os.Parcel;
import android.os.Parcelable;

public class Vehicle implements Parcelable {
                private int  V_ID;
            private  String  V_NU, V_NAME, V_TYPE, V_IMAGE, V_ADD_TIMESTAMP, V_UPDATE_TIMESTAMP ;

            public Vehicle(){

            }

    public Vehicle(int v_ID, String v_NU, String v_NAME, String v_TYPE, String v_IMAGE, String v_ADD_TIMESTAMP, String v_UPDATE_TIMESTAMP) {
        V_ID = v_ID;
        V_NU = v_NU;
        V_NAME = v_NAME;
        V_TYPE = v_TYPE;
        V_IMAGE = v_IMAGE;
        V_ADD_TIMESTAMP = v_ADD_TIMESTAMP;
        V_UPDATE_TIMESTAMP = v_UPDATE_TIMESTAMP;
    }

    public Vehicle(String v_NU, String v_NAME, String v_TYPE, String v_IMAGE, String v_ADD_TIMESTAMP, String v_UPDATE_TIMESTAMP) {
        V_NU = v_NU;
        V_NAME = v_NAME;
        V_TYPE = v_TYPE;
        V_IMAGE = v_IMAGE;
        V_ADD_TIMESTAMP = v_ADD_TIMESTAMP;
        V_UPDATE_TIMESTAMP = v_UPDATE_TIMESTAMP;
    }

    protected Vehicle(Parcel in) {
        V_ID = in.readInt();
        V_NU = in.readString();
        V_NAME = in.readString();
        V_TYPE = in.readString();
        V_IMAGE = in.readString();
        V_ADD_TIMESTAMP = in.readString();
        V_UPDATE_TIMESTAMP = in.readString();
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public int getV_ID() {
        return V_ID;
    }

    public void setV_ID(int v_ID) {
        V_ID = v_ID;
    }

    public String getV_NU() {
        return V_NU;
    }

    public void setV_NU(String v_NU) {
        V_NU = v_NU;
    }

    public String getV_NAME() {
        return V_NAME;
    }

    public void setV_NAME(String v_NAME) {
        V_NAME = v_NAME;
    }

    public String getV_TYPE() {
        return V_TYPE;
    }

    public void setV_TYPE(String v_TYPE) {
        V_TYPE = v_TYPE;
    }

    public String getV_IMAGE() {
        return V_IMAGE;
    }

    public void setV_IMAGE(String v_IMAGE) {
        V_IMAGE = v_IMAGE;
    }

    public String getV_ADD_TIMESTAMP() {
        return V_ADD_TIMESTAMP;
    }

    public void setV_ADD_TIMESTAMP(String v_ADD_TIMESTAMP) {
        V_ADD_TIMESTAMP = v_ADD_TIMESTAMP;
    }

    public String getV_UPDATE_TIMESTAMP() {
        return V_UPDATE_TIMESTAMP;
    }

    public void setV_UPDATE_TIMESTAMP(String v_UPDATE_TIMESTAMP) {
        V_UPDATE_TIMESTAMP = v_UPDATE_TIMESTAMP;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(V_ID);
        dest.writeString(V_NU);
        dest.writeString(V_NAME);
        dest.writeString(V_TYPE);
        dest.writeString(V_IMAGE);
        dest.writeString(V_ADD_TIMESTAMP);
        dest.writeString(V_UPDATE_TIMESTAMP);
    }
}
