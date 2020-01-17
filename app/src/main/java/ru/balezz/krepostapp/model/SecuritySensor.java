package ru.balezz.krepostapp.model;

import android.graphics.drawable.Drawable;

import java.util.UUID;

public class SecuritySensor {
    UUID uuid;
    String mTitle;
    String mDetail;
    Drawable mSensorImage;

    int status;
    int x;
    int y;

    public SecuritySensor() {
        uuid = UUID.randomUUID();
        mTitle = "Empty mTitle";
    }

    public SecuritySensor(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }

    public Drawable getSensorImage() {
        return mSensorImage;
    }

    public void setSensorImage(Drawable sensorImage) {
        mSensorImage = sensorImage;
    }
}
