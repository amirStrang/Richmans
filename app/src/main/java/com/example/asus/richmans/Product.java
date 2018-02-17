package com.example.asus.richmans;

import java.util.ArrayList;
import java.util.List;

class Product {
    String name;
    String price;
    int ID;
    List<Integer> images;
    String description;

    public Product() {
        images = new ArrayList<>();
    }
}
