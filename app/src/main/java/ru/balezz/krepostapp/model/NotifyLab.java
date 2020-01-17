package ru.balezz.krepostapp.model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

import ru.balezz.krepostapp.R;

public class NotifyLab {
    private static NotifyLab sNotifyLab;
    private List<Notify> mNotifies;
    private Context mContext;

    public static NotifyLab getInstance(Context context) {
        if (sNotifyLab == null) sNotifyLab = new NotifyLab(context);
        return sNotifyLab;
    }

    public NotifyLab(Context context) {
        mContext = context;
        generateNotifies();
    }

    public List<Notify> getNotifies() {
        return mNotifies;
    }

    private void generateNotifies() {
        String stubTitle = mContext.getString(R.string.notify_title_stub);
        String stubDetail = mContext.getString(R.string.notify_detail_stub);
        String stubTime = mContext.getString(R.string.notify_time_stub);
        Drawable stubImageDrawable = mContext.getDrawable(R.drawable.notify_red);
        mNotifies = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            Notify notify = new Notify();
            notify.setTitle(stubTitle);
            notify.setDetail(stubDetail + " " + i);
            notify.setTime(stubTime);
            notify.setNotifyImage(stubImageDrawable);
            mNotifies.add(notify);
        }
    }
}
