package com.andyrewlee.beastlist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andyrewlee.beastlist.database.BeastListSchema.BeastsTable;

/**
 * Created by dev1 on 11/23/15.
 */
public class BeastListOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "beastlist.db";

    public BeastListOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + BeastsTable.NAME + "(" +
        "_id integer primary key autoincrement, " +
        BeastsTable.Cols.UUID + ", " +
        BeastsTable.Cols.OBJECTIVE + ", " +
        BeastsTable.Cols.BEASTED + ", " +
        BeastsTable.Cols.CREATED_AT + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
