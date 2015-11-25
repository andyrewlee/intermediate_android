package com.andyrewlee.students;

import java.util.UUID;

/**
 * Created by dev1 on 11/24/15.
 */
public class Student {
    private UUID uuid;
    private String name;

    public Student() {
        this(UUID.randomUUID());
    }

    public Student(UUID id) {
        this.uuid = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
