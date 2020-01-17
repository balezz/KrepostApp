package ru.balezz.krepostapp.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import ru.balezz.krepostapp.R;

public class SchemeLab {
    private static SchemeLab sSchemeLab;
    private List<RoomScheme> mSchemes;
    private Context mContext;

    public static SchemeLab getInstance(Context context) {
        if (sSchemeLab == null) sSchemeLab = new SchemeLab(context);
        return sSchemeLab;
    }

    private SchemeLab(Context context) {
        mContext = context;
        generateSchemes();
    }

    public List<RoomScheme> getSchemes() {
        return mSchemes;
    }

    private void generateSchemes() {
        mSchemes = new ArrayList<>();
        String titleStub = mContext.getString(R.string.scheme_title_stub);
        String detailStub = mContext.getString(R.string.scheme_detail_stub);
        // requires API-21
        Drawable imageStub = mContext.getDrawable(R.drawable.scheme2);
        for (int i = 1; i < 10; i++) {
            RoomScheme roomScheme = new RoomScheme(titleStub + " " + i);
            roomScheme.setDetail(detailStub + " " +  i);
            roomScheme.setImage(imageStub);
            mSchemes.add(roomScheme);
        }
    }
}
