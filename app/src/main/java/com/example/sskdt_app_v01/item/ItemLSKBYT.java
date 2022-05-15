package com.example.sskdt_app_v01.item;


import java.util.Date;

public class ItemLSKBYT {
    private String id;
    private Date date;
    private String name;

    public ItemLSKBYT(Date date, String name) {
        this.date = date;
        this.name = name;
    }

    public ItemLSKBYT(String id, Date date, String name) {
        this.id = id;
        this.date = date;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
