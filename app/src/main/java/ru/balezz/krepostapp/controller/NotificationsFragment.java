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
import ru.balezz.krepostapp.model.Notify;

public class NotificationsFragment extends Fragment {
    private static final String TAG = "NotificationsFragment";
    RecyclerView mRecyclerView;
    List<Notify> mNotifies = new ArrayList<>();

    private class NotifyViewHolder extends RecyclerView.ViewHolder {
        TextView mNotifyTitle;
        TextView mNotifyDetail;
        TextView mNotifyTime;
        ImageView mNotifyImage;
        public NotifyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_notify, parent, false));
            mNotifyTitle = (TextView) itemView.findViewById(R.id.notify_title);
            mNotifyTime = (TextView) itemView.findViewById(R.id.notify_time);
            mNotifyDetail = (TextView) itemView.findViewById(R.id.notify_detail);
            mNotifyImage = (ImageView) itemView.findViewById(R.id.notify_image);
        }

        public void bindNotify(Notify notify) {
            mNotifyTitle.setText(notify.getTitle());
            mNotifyDetail.setText(notify.getDetail());
            mNotifyTime.setText(notify.getDate());
            mNotifyImage.setImageDrawable(notify.getNotifyImage());
        }
    }

    private class NotifyAdapter extends RecyclerView.Adapter<NotifyViewHolder> {
        List<Notify> mNotifies;

        public NotifyAdapter(List<Notify> notifies) {
            mNotifies = notifies;
        }

        @NonNull
        @Override
        public NotifyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new NotifyViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull NotifyViewHolder holder, int position) {
            holder.bindNotify(mNotifies.get(position));
        }

        @Override
        public int getItemCount() {
            return mNotifies.size();
        }
    }

    private class FetchNotifiesTask extends AsyncTask<Void, Void, List<Notify>> {

        @Override
        protected List<Notify> doInBackground(Void... voids) {
            return new Fetcher(getActivity()).fetchNotifies();
        }

        @Override
        protected void onPostExecute(List<Notify> notifies) {
            mNotifies = notifies;
            setupAdapter();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        new FetchNotifiesTask().execute();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.setting_menu, menu);
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        mRecyclerView = root.findViewById(R.id.notify_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        setupAdapter();
        return root;
    }

    private void setupAdapter() {
        if(isAdded()){
            NotifyAdapter adapter = new NotifyAdapter(mNotifies);
            mRecyclerView.setAdapter(adapter);
        }
    }


}