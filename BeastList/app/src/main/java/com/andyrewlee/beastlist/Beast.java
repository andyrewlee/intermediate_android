package com.andyrewlee.beastlist;

import android.support.annotation.Nullable;

import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by dev1 on 11/16/15.
 */
public class Beast {
    private static Object ArrayList;
    private UUID id;
    private String objective;
    private Date createdAt;
    private boolean beasted;

    public Beast() {
        id = UUID.randomUUID();
        createdAt = new Date();
        beasted = false;
    }

    public UUID getId() { return id; }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public boolean isBeasted() {
        return beasted;
    }

    public void setBeasted(boolean beasted) {
        this.beasted = beasted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public static ArrayList<Beast> all() {
        ArrayList<Beast> beasts = new ArrayList<>();

        for(int i = 0; i < 100; i++) {
            Beast beast = new Beast();
            beast.setObjective("Beast # " + i);
            beast.setBeasted(i % 2 == 0);
            beasts.add(beast);
        }

        return beasts;
    }

    @Nullable
    public static Beast find(UUID id) {
        ArrayList<Beast> beasts = Beast.all();

        for(Beast beast: beasts) {
            if(beast.getId().equals(id)) {
                return beast;
            }
        }

        return null;
    }
}
