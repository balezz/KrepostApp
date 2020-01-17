package ru.balezz.krepostapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import ru.balezz.krepostapp.R;
import ru.balezz.krepostapp.database.KrepostBaseHelper;

public class KrepostLab {
    private static KrepostLab sKrepostLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private List<RoomScheme> mSchemes;
    private List<SecuritySensor> mSensors;
    private List<Notify> mNotifies;

    private KrepostLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new KrepostBaseHelper(mContext)
                .getWritableDatabase();

        // temp stubs
        generateSchemes();
        generateSensors();
        generateNotifies();
    }

    public static KrepostLab getInstance(Context context) {
        if (sKrepostLab == null) sKrepostLab = new KrepostLab(context);
        return sKrepostLab;
    }

    public List<RoomScheme> getSchemes() {
        return mSchemes;
    }

    public List<SecuritySensor> getSensors() {
        return mSensors;
    }

    public List<Notify> getNotifies() {
        return mNotifies;
    }

    /** Stub methods */
    private void generateSchemes() {
        mSchemes = new ArrayList<>();
        String titleStub = mContext.getString(R.string.scheme_title_stub);
        String detailStub = mContext.getString(R.string.scheme_detail_stub);
        // requires API-21
        Drawable imageStub = mContext.getDrawable(R.drawable.scheme2);
        for (int i = 1; i < 10; i++) {
            RoomScheme roomScheme = new RoomScheme(titleStub + " " + i);
            roomScheme.setDetail(detailStub + " " +  i);
            roomScheme.setImage(imageStub);
            mSchemes.add(roomScheme);
        }
    }

    private void generateSensors() {
        mSensors = new ArrayList<>();
        String stubTitle = mContext.getString(R.string.sensor_title_stub);
        String stubDetail = mContext.getString(R.string.sensor_detail_stub);
        Drawable stubDrawable = mContext.getDrawable(R.drawable.ic_security_black_24dp);
        for (int i = 1; i < 30; i++) {
            SecuritySensor sensor = new SecuritySensor();
            sensor.setTitle(stubTitle + " " + i);
            sensor.setDetail(stubDetail);
            sensor.setSensorImage(stubDrawable);
            mSensors.add(sensor);
        }
    }

    private void generateNotifies() {
        String stubTitle = mContext.getString(R.string.notify_title_stub);
        String stubDetail = mContext.getString(R.string.notify_detail_stub);
        String stubTime = mContext.getString(R.string.notify_time_stub);
        Drawable stubImageDrawable = mContext.getDrawable(R.drawable.notify_red);
        mNotifies = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            Notify notify = new Notify();
            notify.setTitle(stubTitle);
            notify.setDetail(stubDetail + " " + i);
            notify.setTime(stubTime);
            notify.setNotifyImage(stubImageDrawable);
            mNotifies.add(notify);
        }
    }
}
