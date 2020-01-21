package ru.balezz.krepostapp.controller;

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


import java.util.List;

import ru.balezz.krepostapp.R;
import ru.balezz.krepostapp.connect.Fetcher;
import ru.balezz.krepostapp.model.RoomScheme;

public class HomeFragment extends Fragment {

    RecyclerView mSchemesRecyclerView;
    SchemeAdapter mSchemeAdapter;
    ImageView mSchemeImageView;


    private class SchemeHolder extends RecyclerView.ViewHolder {
        TextView mSchemeTitle;
        TextView mSchemeDetail;

        public SchemeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_scheme, parent, false));
            mSchemeTitle = (TextView) itemView.findViewById(R.id.scheme_title);
            mSchemeDetail = (TextView) itemView.findViewById(R.id.scheme_detail);
        }

        private void bindView(RoomScheme scheme) {
            mSchemeTitle.setText(scheme.getTitle());
            mSchemeDetail.setText(scheme.getDetail());
            mSchemeImageView.setImageDrawable(scheme.getImage());
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
            holder.bindView(mRoomSchemes.get(position));
        }

        @Override
        public int getItemCount() {
            return mRoomSchemes.size();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.setting_menu, menu);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mSchemesRecyclerView = (RecyclerView) root.findViewById(R.id.schemes_recyclerview);
        mSchemesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSchemesRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        mSchemeImageView = root.findViewById(R.id.imageScheme);
        updateUI();
        return root;
    }

    private void updateUI() {
/*        List<RoomScheme> schemes = KrepostLab
                .getInstance(getActivity()).getSchemes();*/
        List<RoomScheme> schemes = new Fetcher(getActivity()).fetchRooms();
                mSchemeAdapter = new SchemeAdapter(schemes);
        mSchemesRecyclerView.setAdapter(mSchemeAdapter);
    }
}