package ru.balezz.krepostapp;

import android.widget.ImageView;

import java.util.List;

public class SensorScheme {
    static SensorScheme sSensorScheme;
    List<SecuritySensor> sensorList;
    ImageView imgBackground;

    public static SensorScheme getInstance() {
        if (sSensorScheme == null) {
            sSensorScheme = new SensorScheme();
        }
            return sSensorScheme;
    }


}
