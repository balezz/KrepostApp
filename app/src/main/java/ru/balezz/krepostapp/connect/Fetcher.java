package ru.balezz.krepostapp.connect;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ru.balezz.krepostapp.R;
import ru.balezz.krepostapp.model.Notify;
import ru.balezz.krepostapp.model.RoomScheme;
import ru.balezz.krepostapp.model.SecuritySensor;

public class Fetcher {
    private static final String TAG = "krepostapp.Fetcher";
    private static final String LIMIT_SENSOR = "limit";
    private static final String NOTIFY_RED = "red";
    private static final String NOTIFY_GREEN = "green";
    private Context mContext;
    private File mFileDirectory;

    public Fetcher(Context context) {
        mContext = context;
        mFileDirectory = mContext.getFilesDir();
    }

    public List<RoomScheme> fetchRooms(){
        List<RoomScheme> roomSchemes = new ArrayList<>();
        String roomFilePath = "rooms.json";
        String roomsJsonString = getJsonString(roomFilePath);
        parseRooms(roomSchemes, roomsJsonString);
        return roomSchemes;
    }

    public List<SecuritySensor> fetchSensors() {
        List<SecuritySensor> sensorsList = new ArrayList<>();
        String sensorsFilePath = "sensors.json";
        String sensorJsonString = getJsonString(sensorsFilePath);
        parseSensors(sensorsList, sensorJsonString);
        return sensorsList;
    }

    public List<Notify> fetchNotifies() {
        List<Notify> notifies = new ArrayList<>();
        String notifyFilePath = "notifies.json";
        String jsonString = getJsonString(notifyFilePath);
        parseNotifies(notifies, jsonString);
        return notifies;
    }


    private void parseRooms(List<RoomScheme> rooms, String roomsJsonString) {
        Log.d(TAG, "parseRooms: " + roomsJsonString);
        try {
            JSONObject roomsBody = new JSONObject(roomsJsonString);
            JSONArray roomsArray = roomsBody.getJSONArray("rooms");
            for (int i = 0; i < roomsArray.length(); i++) {
                RoomScheme roomScheme = new RoomScheme();
                JSONObject room = roomsArray.getJSONObject(i);

                String title = room.getString("title");
                String detail = room.getString("detail");
                String imgName = room.getString("image");

                File file = new File(mFileDirectory, imgName);
                Drawable drawable = new BitmapDrawable(Resources.getSystem(), file.getPath());

                roomScheme.setTitle(title);
                roomScheme.setDetail(detail);
                roomScheme.setImage(drawable);
                rooms.add(roomScheme);
            }
        } catch (JSONException je) {
            Log.e(TAG, "parseRooms: ", je);
        }
    }

    private void parseSensors(List<SecuritySensor> sensors, String sensorsJsonString) {
        Log.d(TAG, "parseSensors: " + sensorsJsonString);
        try {
            JSONObject sensorsBody = new JSONObject(sensorsJsonString);
            JSONArray sensorsArray = sensorsBody.getJSONArray("sensors");
            for (int i = 0; i < sensorsArray.length(); i++) {
                SecuritySensor sensor = new SecuritySensor();
                JSONObject sensorJson = sensorsArray.getJSONObject(i);

                String title = sensorJson.getString("title");
                String detail = sensorJson.getString("detail");
                String sensorImage = sensorJson.getString("image");
                Drawable drawable;

                // todo: sensor image setup logic
                if (sensorImage.equals(LIMIT_SENSOR)) {
                    drawable = mContext.getDrawable(R.drawable.ic_security_black_32dp);
                } else {
                    drawable = mContext.getDrawable(R.drawable.ic_security_black_32dp);
                }

                sensor.setTitle(title);
                sensor.setDetail(detail);
                sensor.setSensorImage(drawable);
                sensors.add(sensor);
            }
        } catch (JSONException je) {
            Log.e(TAG, "parseSensors: ", je);
        }
    }

    private void parseNotifies(List<Notify> notifies, String notifiesJsonString) {
        Log.d(TAG, "parseNotifies: " + notifiesJsonString);
        try {
            JSONObject notifiesBody = new JSONObject(notifiesJsonString);
            JSONArray notifiesArray = notifiesBody.getJSONArray("notifies");
            for (int i = 0; i < notifiesArray.length(); i++) {
                Notify notify = new Notify();
                JSONObject notifyJson = notifiesArray.getJSONObject(i);

                String title = notifyJson.getString("title");
                String detail = notifyJson.getString("detail");
                String date = notifyJson.getString("date");
                String notifyImage = notifyJson.getString("image");
                Drawable drawable;

                // todo: sensor image setup logic
                if (notifyImage.equals(NOTIFY_RED)) {
                    drawable = mContext.getDrawable(R.drawable.notify_red);
                } else {
                    drawable = mContext.getDrawable(R.drawable.notify_green);
                }

                notify.setTitle(title);
                notify.setDetail(detail);
                notify.setDate(date);
                notify.setNotifyImage(drawable);
                notifies.add(notify);
            }
        } catch (JSONException je) {
            Log.e(TAG, "parseSensors: ", je);
        }
    }


    /** Stub method, now get from file, later will get with REST API */
    private String getJsonString(String fileName) {
        Log.d(TAG, "getJsonString: " + fileName);
        File file = new File(mFileDirectory, fileName);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
            }
            br.close();
        } catch (IOException ioe){
            Log.e(TAG, "getJsonString: ", ioe);
        }

        Log.i(TAG, "getJsonString: " + text);
        return new String(text);
    }

}
