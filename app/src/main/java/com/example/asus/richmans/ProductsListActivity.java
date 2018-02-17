package com.example.asus.richmans;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class ProductsListActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        init();
    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recProducts = (RecyclerView) findViewById(R.id.rec_products);
        List<Product> products = new ArrayList<>();

        //sample init
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.name = "موبایل";
            product.price = "1000000";
            product.description = "یه گوشی خیلی خوب :)";
            product.ID = 1;
            product.images.add(R.drawable.sample4);
            products.add(product);
        }

//        //initialize list
//        for (int i = 0; i < length; i++) {
//            Product product = new Product();
//            product.name = ...;
//            product.ID = ....;
//            product.images = ....;
//            product.price = ....;
//            product.description = ...;
//            products.add(product);
//        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recProducts.setLayoutManager(linearLayoutManager);
        recProducts.setHasFixedSize(true);
        recProducts.setAdapter(new ProductRecyclerAdapter(getApplicationContext(), products));
        DividerItemDecoration itemDecor = new DividerItemDecoration(this, VERTICAL);
        recProducts.addItemDecoration(itemDecor);

    }
}
