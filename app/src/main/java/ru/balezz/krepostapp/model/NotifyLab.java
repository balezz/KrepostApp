package ru.balezz.krepostapp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class NotifyLab {
    private static NotifyLab sNotifyLab;
    private List<Notify> mNotifies;

    private NotifyLab() {
        generateNotifies();
    }

    public static NotifyLab getInstance(Context context) {
        if (sNotifyLab == null) sNotifyLab = new NotifyLab();
        return sNotifyLab;
    }

    public List<Notify> getNotifies() {
        return mNotifies;
    }

    private void generateNotifies() {
        mNotifies = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Notify notify = new Notify();
            mNotifies.add(notify);
        }
    }
}
