package ru.balezz.krepostapp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


public class SensorLab {
    private static SensorLab sSensorLab;
    private List<SecuritySensor> mSensors;

    public static SensorLab getInstance(Context context) {
        if (sSensorLab == null) sSensorLab = new SensorLab();
        return sSensorLab;
    }

    public SensorLab() {
        generateSensors();
    }

    public List<SecuritySensor> getSensors() {
        return mSensors;
    }

    private void generateSensors() {
        mSensors = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            SecuritySensor sensor = new SecuritySensor();
            mSensors.add(sensor);
        }
    }


}
