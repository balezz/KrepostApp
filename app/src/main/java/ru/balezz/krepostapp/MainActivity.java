package ru.balezz.krepostapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment sensor_fragment = fm.findFragmentById(R.id.sensor_fragment_id);
        Fragment scheme_fragment = fm.findFragmentById(R.id.scheme_fragment_id);

        if (sensor_fragment == null) {
            sensor_fragment = new SensorFragment();
            fm.beginTransaction()
                    .add(R.id.sensor_fragment_id, sensor_fragment)
                    .commit();
        }

        if (scheme_fragment == null) {
            scheme_fragment = new SchemeFragment();
            fm.beginTransaction()
                    .add(R.id.scheme_fragment_id, scheme_fragment)
                    .commit();
        }
    }
}
