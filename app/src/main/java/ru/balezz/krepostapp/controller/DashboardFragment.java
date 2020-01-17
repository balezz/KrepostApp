package ru.balezz.krepostapp.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.balezz.krepostapp.R;
import ru.balezz.krepostapp.model.KrepostLab;
import ru.balezz.krepostapp.model.SecuritySensor;

public class DashboardFragment extends Fragment {

    RecyclerView mSensorsRecyclerView;

    private class SensorHolder extends RecyclerView.ViewHolder {
        TextView mSensorTitle;
        TextView mSensorDetail;
        ImageView mSensorImage;

        public SensorHolder(LayoutInflater inflater, @NonNull ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_sensor, parent, false));
            mSensorTitle = (TextView) itemView.findViewById(R.id.sensor_title);
            mSensorDetail = (TextView) itemView.findViewById(R.id.sensor_detail);
            mSensorImage = (ImageView) itemView.findViewById(R.id.sensor_image);
        }

        public void bindSensor(SecuritySensor sensor) {
            mSensorTitle.setText(sensor.getTitle());
            mSensorDetail.setText(sensor.getDetail());
            mSensorImage.setImageDrawable(sensor.getSensorImage());
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
            holder.bindSensor(mSensors.get(position));
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
        mSensorsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        updateUI();
        return root;
    }

    private void updateUI() {
        List<SecuritySensor> sensors = KrepostLab
                .getInstance(getActivity()).getSensors();
        SensorAdapter adapter = new SensorAdapter(sensors);
        mSensorsRecyclerView.setAdapter(adapter);
    }
}
