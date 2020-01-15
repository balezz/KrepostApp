package ru.balezz.krepostapp.model;

import android.widget.ImageView;

import java.util.List;

public class RoomScheme {
    ImageView imgBackground;
    String mCapture;

    public RoomScheme(String capture) {
        mCapture = capture;
    }

    public String getCapture() {
        return mCapture;
    }
}
