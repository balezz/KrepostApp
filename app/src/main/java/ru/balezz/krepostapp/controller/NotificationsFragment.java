package ru.balezz.krepostapp.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.balezz.krepostapp.R;
import ru.balezz.krepostapp.model.Notify;
import ru.balezz.krepostapp.model.NotifyLab;

public class NotificationsFragment extends Fragment {

    RecyclerView mRecyclerView;

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
            mNotifyTime.setText(notify.getTime());
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        mRecyclerView = root.findViewById(R.id.notify_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return root;
    }

    private void updateUI() {
        List<Notify> notifies = NotifyLab.getInstance(getActivity()).getNotifies();
        NotifyAdapter adapter = new NotifyAdapter(notifies);
        mRecyclerView.setAdapter(adapter);
    }


}