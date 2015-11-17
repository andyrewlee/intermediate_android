package com.andyrewlee.beastlist;

import android.support.annotation.Nullable;
import android.util.Log;

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
}
