package com.andyrewlee.beastlist;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by dev1 on 11/17/15.
 */
public class BeastsFactory {
    private static BeastsFactory beastsFactory;
    private ArrayList<Beast> beasts;

    public static BeastsFactory get(Context context) {
        if(beastsFactory == null) {
            beastsFactory = new BeastsFactory(context);
        }

        return beastsFactory;
    }

    private BeastsFactory(Context context) {
        beasts = new ArrayList<>();

        for(int i = 0; i < 50; i++) {
            Beast beast = new Beast();
            beast.setObjective("Beast #" + i);
            beast.setBeasted(i % 2 == 0);
            beasts.add(beast);
        }
    }

    public ArrayList<Beast> all() {
        return beasts;
    }

    public Beast find(UUID id) {
        for(Beast beast: beasts) {
            if(beast.getId().equals(id)) {
                return beast;
            }
        }
        return null;
    }
}
