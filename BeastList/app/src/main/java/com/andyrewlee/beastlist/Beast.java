package com.andyrewlee.beastlist;

import java.util.UUID;

/**
 * Created by dev1 on 11/16/15.
 */
public class Beast {
    private UUID id;
    private String objective;

    public Beast() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }
}
