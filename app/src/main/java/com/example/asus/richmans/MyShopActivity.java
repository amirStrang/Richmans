package com.example.asus.richmans;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class MyShopActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recProducts;
    FloatingActionButton btnAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);

        init();

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddNewProductActivity.class);
                startActivity(i);
            }
        });
    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAddNew = (FloatingActionButton) findViewById(R.id.btn_add_new);

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
