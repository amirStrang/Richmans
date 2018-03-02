package com.example.asus.richmans;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recSearchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.requestFocus();
        searchView.setQueryHint("جستوجو");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recSearchResult = (RecyclerView) findViewById(R.id.rec_search_result);
        List<Product> products = new ArrayList<>();

        //sample init
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.name = "موبایل";
            product.price = "1000000";
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
//        recProducts.setLayoutManager(linearLayoutManager);
//        recProducts.setHasFixedSize(true);
//        recProducts.setAdapter(new ProductRecyclerAdapter(getApplicationContext(), products));
//        DividerItemDecoration itemDecor = new DividerItemDecoration(this, VERTICAL);
//        recProducts.addItemDecoration(itemDecor);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
