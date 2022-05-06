package com.example.sskdt_app_v01.item;


import java.util.Date;

public class ItemLSKBYT {
    private Date date;
    private String name;

    public ItemLSKBYT(Date date, String name) {
        this.date = date;
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
