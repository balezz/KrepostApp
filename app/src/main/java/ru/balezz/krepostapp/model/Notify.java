package ru.balezz.krepostapp.model;

import android.graphics.drawable.Drawable;

import java.util.UUID;

public class Notify {
    UUID mUUID;
    private String mTitle;
    private String mDetail;
    private String mDate;
    private Drawable mNotifyImage;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public Drawable getNotifyImage() {
        return mNotifyImage;
    }

    public void setNotifyImage(Drawable notifyImage) {
        mNotifyImage = notifyImage;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
