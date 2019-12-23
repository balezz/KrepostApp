package ru.balezz.krepostapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SensorFragment extends Fragment {
    private SecuritySensor securitySensor;
    private TextView mDescription;
    private TextView mStatus;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        securitySensor = new SecuritySensor();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sensor, container, false);
        mDescription = (TextView) v.findViewById(R.id.textDescription);
        mStatus = (TextView) v.findViewById(R.id.textStatus);


        return v;
    }
}
