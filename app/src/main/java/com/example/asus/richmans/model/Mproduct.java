package com.example.asus.richmans.model;

/**
 * Created by Mr.Anonymous on 2/24/2018.
 */

import java.util.ArrayList;

public class Mproduct {
    private String name, price, category, desc;
    String thumbnailUrl1, thumbnailUrl2, thumbnailUrl3;

    public Mproduct() {
    }

    public Mproduct(String name, String thumbnailUrl1, String price, String cat, String desc) {
        this.name = name;
        this.thumbnailUrl1 = thumbnailUrl1;
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
        return thumbnailUrl1;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl1 = thumbnailUrl;
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

/////////////////////////////////

    public String getThumbnailUrl2() {
        return thumbnailUrl2;
    }

    public void setThumbnailUrl2(String thumbnailUrl) {
        this.thumbnailUrl2 = thumbnailUrl;
    }

    public String getThumbnailUrl3() {
        return thumbnailUrl3;
    }

    public void setThumbnailUrl3(String thumbnailUrl) {
        this.thumbnailUrl3 = thumbnailUrl;
    }


}