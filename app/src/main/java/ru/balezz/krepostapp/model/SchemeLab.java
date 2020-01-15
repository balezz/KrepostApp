package ru.balezz.krepostapp.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class SchemeLab {
    public static final String STUB_CAPTURE = "Помещение № ххх";
    private static SchemeLab sSchemeLab;
    private List<RoomScheme> mSchemes;

    public static SchemeLab getInstance(Context context) {
        if (sSchemeLab == null) sSchemeLab = new SchemeLab();
        return sSchemeLab;
    }

    private SchemeLab() {
        generateSchemes();
    }

    public List<RoomScheme> getSchemes() {
        return mSchemes;
    }

    private void generateSchemes() {
        mSchemes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RoomScheme roomScheme = new RoomScheme(STUB_CAPTURE);
            mSchemes.add(roomScheme);
        }
    }
}
