package ru.balezz.krepostapp.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import ru.balezz.krepostapp.R;
import ru.balezz.krepostapp.model.RoomScheme;
import ru.balezz.krepostapp.model.SchemeLab;

public class HomeFragment extends Fragment {

    RecyclerView mSchemesRecyclerView;
    SchemeAdapter mSchemeAdapter;

    private class SchemeHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public SchemeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_scheme, parent, false));
        }

        private void bindView(RoomScheme scheme) {
            mTextView.setText(scheme.getCapture());
        }
    }

    private class SchemeAdapter extends RecyclerView.Adapter<SchemeHolder> {
        List<RoomScheme> mRoomSchemes;

        public SchemeAdapter(List<RoomScheme> roomSchemes) {
            mRoomSchemes = roomSchemes;
        }

        @NonNull
        @Override
        public SchemeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new SchemeHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull SchemeHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mRoomSchemes.size();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mSchemesRecyclerView = (RecyclerView) root.findViewById(R.id.schemes_recyclerview);
        mSchemesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return root;
    }

    private void updateUI() {
        SchemeLab schemeLab = SchemeLab.getInstance(getActivity());
        List<RoomScheme> schemes = schemeLab.getSchemes();
        mSchemeAdapter = new SchemeAdapter(schemes);
        mSchemesRecyclerView.setAdapter(mSchemeAdapter);
    }
}