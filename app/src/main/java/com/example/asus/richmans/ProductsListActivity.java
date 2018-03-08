package com.example.asus.richmans;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.asus.richmans.adapter.GproductCustomListAdapter;
import com.example.asus.richmans.app.AppController;
import com.example.asus.richmans.model.Gproduct;
import com.example.asus.richmans.model.Mproduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductsListActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recProducts;

    private static final String TAG = ProductsListActivity.class.getSimpleName();

    // Gproducts json url
    String url = "http://ahmadiTest.sepantahost.com/api/GlobalStore?Data=";
    private ProgressDialog pDialog;
    private List<Gproduct> productList = new ArrayList<Gproduct>();
    private ListView listView;
    private GproductCustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_list);

        init();
        listView = (ListView) findViewById(R.id.list);
        adapter = new GproductCustomListAdapter(this, productList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.show();

        // Creating volley request obj
        JsonArrayRequest productReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Gproduct product = new Gproduct();
                                product.setName(obj.getString("Name"));
                                product.setThumbnailUrl(obj.getString("Image1"));
//                                product.setThumbnailUrl(obj.getString("Image1"));
//                                product.setThumbnailUrl(obj.getString("Image1"));
                                product.setPrice(obj.getString("Price"));
                                product.setDesc(obj.getString("Note"));
                                productList.add(product);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(productReq);

        ////////////////////////////////////// click item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ProductsListActivity.this, ProductDetailActivity.class);
                Gproduct mproduct = (Gproduct) parent.getAdapter().getItem(position);
                i.putExtra("product", new String[]{
                        mproduct.getName(),
                        mproduct.getPrice(),
                        mproduct.getDesc(),
                        mproduct.getThumbnailUrl()});
                startActivity(i);
            }
        });
    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //func handle url

//        url += getIntent().getStringExtra("URI");

        url += "5";

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void tt(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
