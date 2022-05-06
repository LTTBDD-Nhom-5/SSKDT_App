package com.example.sskdt_app_v01.model;

import java.util.Date;

public class HealthDeclaration {
    private String nameUser;
    private Date timestamp;

    public HealthDeclaration(String nameUser, Date timestamp) {
        this.nameUser = nameUser;
        this.timestamp = timestamp;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
