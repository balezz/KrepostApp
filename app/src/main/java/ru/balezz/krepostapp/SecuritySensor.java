package ru.balezz.krepostapp;

import java.util.UUID;

public class SecuritySensor {
    UUID uuid;
    String description;
    int status;
    int x;
    int y;

    public SecuritySensor() {
        uuid = UUID.randomUUID();
        description = "Empty description";
    }

    public SecuritySensor(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
