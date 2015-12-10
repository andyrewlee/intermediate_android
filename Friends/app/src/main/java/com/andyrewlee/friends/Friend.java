package com.andyrewlee.friends;

import java.util.UUID;

/**
 * Created by dev1 on 12/1/15.
 */
public class Friend {
    private UUID id;
    private String name;

    public Friend() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
