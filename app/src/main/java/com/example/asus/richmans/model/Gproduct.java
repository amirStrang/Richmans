package com.example.asus.richmans.model;

/**
 * Created by Mr.Anonymous on 2/24/2018.
 */

import java.util.ArrayList;

public class Gproduct {
    private String code, name, price, desc;
    String thumbnailUrl, thumbnailUrl2, thumbnailUrl3;

    public Gproduct() {
    }

    public Gproduct(String name, String thumbnailUrl, String price, String desc) {
        this.name = name;
        this.thumbnailUrl = thumbnailUrl;
        this.price = price;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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