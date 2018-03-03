package com.example.asus.richmans.model;

/**
 * Created by Mr.Anonymous on 2/24/2018.
 */

import java.util.ArrayList;

public class Mproduct {
    private String name, thumbnailUrl, price, category, desc;

    public Mproduct() {
    }

    public Mproduct(String name, String thumbnailUrl, String price, String cat, String desc) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.category = cat;
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

    public String getCat() {
        return category;
    }

    public void setCat(String cat) {
        this.category = cat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}