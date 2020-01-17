package ru.balezz.krepostapp.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import ru.balezz.krepostapp.R;


public class SensorLab {
    private static SensorLab sSensorLab;
    private List<SecuritySensor> mSensors;
    private Context mContext;

    public static SensorLab getInstance(Context context) {
        if (sSensorLab == null) sSensorLab = new SensorLab(context);
        return sSensorLab;
    }

    public SensorLab(Context context) {
        mContext = context;
        generateSensors();
    }

    public List<SecuritySensor> getSensors() {
        return mSensors;
    }

    private void generateSensors() {
        mSensors = new ArrayList<>();
        String stubTitle = mContext.getString(R.string.sensor_title_stub);
        String stubDetail = mContext.getString(R.string.sensor_detail_stub);
        Drawable stubDrawable = mContext.getDrawable(R.drawable.ic_settings_black);
        for (int i = 1; i < 30; i++) {
            SecuritySensor sensor = new SecuritySensor();
            sensor.setTitle(stubTitle + " " + i);
            sensor.setDetail(stubDetail);
            sensor.setSensorImage(stubDrawable);
            mSensors.add(sensor);
        }
    }


}
