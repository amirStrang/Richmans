package com.example.asus.richmans.model;

/**
 * Created by Mr.Anonymous on 2/24/2018.
 */

import java.util.ArrayList;

public class Gproduct {
    private String name, thumbnailUrl, price, desc;

    public Gproduct() {
    }

    public Gproduct(String name, String thumbnailUrl, String price, String desc) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}