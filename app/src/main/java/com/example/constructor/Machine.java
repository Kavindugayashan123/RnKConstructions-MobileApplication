package com.example.constructor;

import android.os.Parcel;
import android.os.Parcelable;

public class Machine implements Parcelable {
    private int M_ID;
    private String M_NUM, M_NAME, M_IMAGE, M_ADD_TIMESTAMP, M_UPDATE_TIMESTAMP, M_SITE;

    public Machine() {

    }

    public Machine(int m_ID, String m_NUM, String m_NAME, String m_IMAGE, String m_ADD_TIMESTAMP, String m_UPDATE_TIMESTAMP, String m_SITE) {
        M_ID = m_ID;
        M_NUM = m_NUM;
        M_NAME = m_NAME;
        M_IMAGE = m_IMAGE;
        M_ADD_TIMESTAMP = m_ADD_TIMESTAMP;
        M_UPDATE_TIMESTAMP = m_UPDATE_TIMESTAMP;
        M_SITE = m_SITE;
    }

    public Machine(String m_NUM, String m_NAME, String m_IMAGE, String m_ADD_TIMESTAMP, String m_UPDATE_TIMESTAMP, String m_SITE) {
        M_NUM = m_NUM;
        M_NAME = m_NAME;
        M_IMAGE = m_IMAGE;
        M_ADD_TIMESTAMP = m_ADD_TIMESTAMP;
        M_UPDATE_TIMESTAMP = m_UPDATE_TIMESTAMP;
        M_SITE = m_SITE;
    }

    protected Machine(Parcel in) {
        M_ID = in.readInt();
        M_NUM = in.readString();
        M_NAME = in.readString();
        M_IMAGE = in.readString();
        M_ADD_TIMESTAMP = in.readString();
        M_UPDATE_TIMESTAMP = in.readString();
        M_SITE = in.readString();
    }

    public static final Creator<Machine> CREATOR = new Creator<Machine>() {
        @Override
        public Machine createFromParcel(Parcel in) {
            return new Machine(in);
        }

        @Override
        public Machine[] newArray(int size) {
            return new Machine[size];
        }
    };

    public int getM_ID() {
        return M_ID;
    }

    public void setM_ID(int m_ID) {
        M_ID = m_ID;
    }

    public String getM_NUM() {
        return M_NUM;
    }

    public void setM_NUM(String m_NUM) {
        M_NUM = m_NUM;
    }

    public String getM_NAME() {
        return M_NAME;
    }

    public void setM_NAME(String m_NAME) {
        M_NAME = m_NAME;
    }

    public String getM_IMAGE() {
        return M_IMAGE;
    }

    public void setM_IMAGE(String m_IMAGE) {
        M_IMAGE = m_IMAGE;
    }

    public String getM_ADD_TIMESTAMP() {
        return M_ADD_TIMESTAMP;
    }

    public void setM_ADD_TIMESTAMP(String m_ADD_TIMESTAMP) {
        M_ADD_TIMESTAMP = m_ADD_TIMESTAMP;
    }

    public String getM_UPDATE_TIMESTAMP() {
        return M_UPDATE_TIMESTAMP;
    }

    public void setM_UPDATE_TIMESTAMP(String m_UPDATE_TIMESTAMP) {
        M_UPDATE_TIMESTAMP = m_UPDATE_TIMESTAMP;
    }

    public String getM_SITE() {
        return M_SITE;
    }

    public void setM_SITE(String m_SITE) {
        M_SITE = m_SITE;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(M_ID);
        dest.writeString(M_NUM);
        dest.writeString(M_NAME);
        dest.writeString(M_IMAGE);
        dest.writeString(M_ADD_TIMESTAMP);
        dest.writeString(M_UPDATE_TIMESTAMP);
        dest.writeString(M_SITE);
    }
}