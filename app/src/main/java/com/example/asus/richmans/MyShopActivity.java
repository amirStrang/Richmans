package com.example.asus.richmans;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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
import com.example.asus.richmans.adapter.MproductCustomListAdapter;
import com.example.asus.richmans.app.AppController;
import com.example.asus.richmans.model.Mproduct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MyShopActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton btnAddNew;

    private static final String TAG = MyShopActivity.class.getSimpleName();

    // Mproducts json url
    String url;
    private ProgressDialog pDialog;
    private List<Mproduct> productList = new ArrayList<Mproduct>();
    private ListView listView;
    private MproductCustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shop);

        init();

        String phn = readFileAsString(getBaseContext(), getFilesDir().getAbsolutePath() + "/.richmans/phn.txt");
        url = "http://seyyedmahdi.eu-4.evennode.com/getproduct/whit/" + phn;

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddNewProductActivity.class);
                startActivity(i);
            }
        });
        listView = (ListView) findViewById(R.id.list);
        adapter = new MproductCustomListAdapter(this, productList);
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
                        for (int i = response.length() - 1; i >= 0; i--) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Mproduct product = new Mproduct();
                                product.setCode(obj.getString("_id"));
                                product.setName(obj.getString("name"));
                                product.setPrice(obj.getString("price"));
                                product.setCat(obj.getString("daste"));
                                product.setDesc(obj.getString("comment"));
                                JSONArray pic = obj.getJSONArray("pictures");
                                product.setThumbnailUrl("http://" + pic.getString(0));
                                product.setThumbnailUrl2("http://" + pic.getString(1));
                                product.setThumbnailUrl3("http://" + pic.getString(2));
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
                Intent i = new Intent(MyShopActivity.this, ProductDetailActivity.class);
                Mproduct mproduct = (Mproduct) parent.getAdapter().getItem(position);
                i.putExtra("product", new String[]{
                        mproduct.getName(),
                        mproduct.getPrice(),
                        mproduct.getDesc(),
                        mproduct.getThumbnailUrl(),
                        mproduct.getThumbnailUrl2(),
                        mproduct.getThumbnailUrl3(),
                        mproduct.getCode()
                });
                startActivity(i);
            }
        });
    }

    void tt(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAddNew = (FloatingActionButton) findViewById(R.id.btn_add_new);

        List<Product> products = new ArrayList<>();

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

    public String readFileAsString(Context context, String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(filePath)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);
        } catch (FileNotFoundException e) {
            //
        } catch (IOException e) {
            //
        }

        return stringBuilder.toString();
    }
}
