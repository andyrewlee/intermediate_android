package com.andyrewlee.beastlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.andyrewlee.beastlist.database.BeastCursorWrapper;
import com.andyrewlee.beastlist.database.BeastListOpenHelper;
import com.andyrewlee.beastlist.database.BeastListSchema.BeastsTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by dev1 on 11/17/15.
 */
public class BeastsFactory {
    private static BeastsFactory beastsFactory;

    private Context context;
    private SQLiteDatabase database;

    private static ContentValues getContentValues(Beast beast) {
        ContentValues values = new ContentValues();

        values.put(BeastsTable.Cols.UUID, beast.getId().toString());
        values.put(BeastsTable.Cols.OBJECTIVE, beast.getObjective());
        values.put(BeastsTable.Cols.CREATED_AT, beast.getCreatedAt().getTime());
        values.put(BeastsTable.Cols.BEASTED, beast.isBeasted() ? 1 : 0);

        return values;
    }

    public static BeastsFactory get(Context context) {
        if(beastsFactory == null) {
            beastsFactory = new BeastsFactory(context);
        }

        return beastsFactory;
    }

    public void addBeast(Beast beast) {
        Log.d("addBeast", "yay");
        ContentValues values = getContentValues(beast);
        Log.d("addBeast", "jay");

        // first argument is table you want to insert into
        // second argument of null prevents insert if the values are null
        // last argument is data you want to put in
        database.insert(BeastsTable.NAME, null, values);
    }

    public void updateBeast(Beast beast) {
        String uuidString = beast.getId().toString();
        ContentValues values = getContentValues(beast);

        // first argument is table you want to update
        // second argument is data you want to update
        // third argument is specify which row by telling where
        // fourth argument is the values for the arguments in the where clause
        // prevents sql injection
        database.update(BeastsTable.NAME, values, BeastsTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    private BeastCursorWrapper queryBeasts(String whereClause, String[] whereArgs) {
        Cursor cursor = database.query(
                BeastsTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // group by
                null, // having
                null  // order by
        );

        return new BeastCursorWrapper(cursor);
    }

    private BeastsFactory(Context context) {
        context = context.getApplicationContext();
        database = new BeastListOpenHelper(context).getWritableDatabase();
    }

    public ArrayList<Beast> all() {
        ArrayList<Beast> beasts = new ArrayList<>();
        BeastCursorWrapper cursor = queryBeasts(null, null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                beasts.add(cursor.getBeast());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return beasts;
    }

    public Beast find(UUID id) {
        BeastCursorWrapper cursor = queryBeasts(BeastsTable.Cols.UUID + " = ?",
                                                new String[] { id.toString() });

        try {
            if(cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getBeast();
        } finally {
            cursor.close();
        }
    }

    public void destroy(Beast beast) {
        database.delete(BeastsTable.NAME, BeastsTable.Cols.UUID + " = ?", new String[] { beast.getId().toString() });
    }
}