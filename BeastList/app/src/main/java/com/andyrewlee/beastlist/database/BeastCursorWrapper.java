package com.andyrewlee.beastlist.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.andyrewlee.beastlist.Beast;
import com.andyrewlee.beastlist.database.BeastListSchema.BeastsTable;

import java.util.Date;
import java.util.UUID;

/**
 * Created by dev1 on 11/23/15.
 */
public class BeastCursorWrapper extends CursorWrapper {

    public BeastCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Beast getBeast() {
        String uuidString = getString(getColumnIndex(BeastsTable.Cols.UUID));
        String objective = getString(getColumnIndex(BeastsTable.Cols.OBJECTIVE));
        long createdAt = getLong(getColumnIndex(BeastsTable.Cols.CREATED_AT));
        int beasted = getInt(getColumnIndex(BeastsTable.Cols.BEASTED));

        Beast beast = new Beast(UUID.fromString(uuidString));
        beast.setObjective(objective);
        beast.setCreatedAt(new Date(createdAt));
        beast.setBeasted(beasted != 0);

        return beast;
    }
}
