package ru.balezz.krepostapp.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.UUID;

public class RoomScheme {
    UUID mUUID;
    String mTitle;
    String mDetail;
    Drawable mImage;

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable image) {
        mImage = image;
    }

    public RoomScheme() {
    }

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
}
