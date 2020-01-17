package ru.balezz.krepostapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import ru.balezz.krepostapp.model.KrepostLab;

import static ru.balezz.krepostapp.database.KrepostDbSchema.*;

public class KrepostBaseHelper extends SQLiteOpenHelper {
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "krepostBase.db";

    public KrepostBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + RoomsTable.NAME
                + "(" + "_id integer primary key autoincrement, " +
                RoomsTable.Cols.ID + ", " +
                RoomsTable.Cols.TITLE + ", " +
                RoomsTable.Cols.DETAIL + ", " +
                RoomsTable.Cols.IMAGE + ", " +
                RoomsTable.Cols.SENSORS + ")"
        );

        db.execSQL("create table " + SensorsTable.NAME
                + "(" + "_id integer primary key autoincrement, " +
                SensorsTable.Cols.ID + ", " +
                SensorsTable.Cols.TITLE + ", " +
                SensorsTable.Cols.DETAIL + ", " +
                SensorsTable.Cols.IMAGE + ")"
        );

        db.execSQL("create table " + NotifiesTable.NAME
                + "(" + "_id integer primary key autoincrement, " +
                NotifiesTable.Cols.ID + ", " +
                NotifiesTable.Cols.TITLE + ", " +
                NotifiesTable.Cols.DETAIL + ", " +
                NotifiesTable.Cols.DATE + ", " +
                NotifiesTable.Cols.IMAGE + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
