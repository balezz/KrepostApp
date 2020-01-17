package ru.balezz.krepostapp.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class RoomScheme {
    ImageView imgBackground;
    String mTitle;
    String mDetail;
    Drawable mImage;

    public Drawable getImage() {
        return mImage;
    }

    public void setImage(Drawable image) {
        mImage = image;
    }

    public RoomScheme(String title) {
        mTitle = title;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDetail() {
        return mDetail;
    }

    public void setDetail(String detail) {
        mDetail = detail;
    }
}
