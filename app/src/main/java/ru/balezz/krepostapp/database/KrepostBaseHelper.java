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
        db.execSQL("create table " + RoomsTable.NAME);
        db.execSQL("create table " + SensorsTable.NAME);
        db.execSQL("create table " + NotifiesTable.NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
