package com.example.asus.richmans.model;

/**
 * Created by Mr.Anonymous on 3/3/2018.
 */

import java.util.ArrayList;

public class History {
    private String from, to, day;

    public History() {
    }

    public History(String from, String to, String day) {
        this.from = from;
        this.to = to;
        this.day = day;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}