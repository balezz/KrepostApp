package ru.balezz.krepostapp.controller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

import java.util.ArrayList;
import java.util.List;

import ru.balezz.krepostapp.R;
import ru.balezz.krepostapp.connect.Fetcher;
import ru.balezz.krepostapp.model.SecuritySensor;

public class DashboardFragment extends Fragment {
    private static final String TAG = "DashboardFragment";

    RecyclerView mSensorsRecyclerView;
    List<SecuritySensor> mSensors = new ArrayList<>();

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

    private class FetchSensorsTask extends AsyncTask<Void, Void, List<SecuritySensor>> {

        @Override
        protected List<SecuritySensor> doInBackground(Void... voids) {
            return new Fetcher(getActivity()).fetchSensors();
        }

        @Override
        protected void onPostExecute(List<SecuritySensor> sensors) {
            mSensors = sensors;
            setupAdapter();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        new FetchSensorsTask().execute();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.setting_menu, menu);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mSensorsRecyclerView = root.findViewById(R.id.sensors_recyclerview);
        mSensorsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSensorsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        setupAdapter();
        return root;
    }

    private void setupAdapter() {
        if (isAdded()){
            SensorAdapter adapter = new SensorAdapter(mSensors);
            mSensorsRecyclerView.setAdapter(adapter);
        }
    }
}
