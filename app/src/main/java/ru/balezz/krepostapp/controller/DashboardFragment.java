package ru.balezz.krepostapp.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.balezz.krepostapp.R;
import ru.balezz.krepostapp.model.SecuritySensor;
import ru.balezz.krepostapp.model.SensorLab;

public class DashboardFragment extends Fragment {

    RecyclerView mSensorsRecyclerView;

    private class SensorHolder extends RecyclerView.ViewHolder {
        public SensorHolder(LayoutInflater inflater, @NonNull ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_sensor, parent, false));
        }
    }

    private class SensorAdapter extends RecyclerView.Adapter<SensorHolder> {
        List<SecuritySensor> mSensors;

        public SensorAdapter(List<SecuritySensor> sensors) {
            mSensors = sensors;
        }

        @NonNull
        @Override
        public SensorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SensorHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SensorHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mSensors.size();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mSensorsRecyclerView = root.findViewById(R.id.sensors_recyclerview);
        mSensorsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return root;
    }

    private void updateUI() {
        List<SecuritySensor> sensors = SensorLab.getInstance(getActivity()).getSensors();
        SensorAdapter adapter = new SensorAdapter(sensors);
        mSensorsRecyclerView.setAdapter(adapter);
    }
}
