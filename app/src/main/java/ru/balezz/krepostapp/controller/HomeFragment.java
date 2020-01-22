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
import ru.balezz.krepostapp.model.RoomScheme;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";

    RecyclerView mSchemesRecyclerView;
    SchemeAdapter mSchemeAdapter;
    ImageView mSchemeImageView;
    List<RoomScheme> mSchemes = new ArrayList<>();

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

    private class FetchRoomsTask extends AsyncTask<Void, Void, List<RoomScheme>> {
        @Override
        protected List<RoomScheme> doInBackground(Void... voids) {
            List<RoomScheme> roomSchemes = new Fetcher(getContext())
                    .fetchRooms();
            return roomSchemes;
        }

        @Override
        protected void onPostExecute(List<RoomScheme> roomSchemes) {
            mSchemes = roomSchemes;
            setupAdapter();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        new FetchRoomsTask().execute();
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
        setupAdapter();
        return root;
    }

    private void setupAdapter() {
/*        List<RoomScheme> schemes = KrepostLab
                .getInstance(getActivity()).getSchemes();*/
        if (isAdded()) {
            mSchemeAdapter = new SchemeAdapter(mSchemes);
            mSchemesRecyclerView.setAdapter(mSchemeAdapter);
        }

    }
}