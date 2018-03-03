package com.example.asus.richmans;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class HistoryTab1 extends Fragment {


    private static final String TAG = "Tab1";

    // Mproducts json url
    private ProgressDialog pDialog;
    private List<Mproduct> productList = new ArrayList<Mproduct>();
    private ListView listView;
    private MproductCustomListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_tab1, container, false);

        listView = (ListView) rootView.findViewById(R.id.list);
        adapter = new MproductCustomListAdapter(getActivity(), productList);
        listView.setAdapter(adapter);

        ///////////////////////////////// get phn
        String phn = readFileAsString(getContext(),
                getContext().getFilesDir().getAbsolutePath() + "/.richmans/phn.txt");
        getproduct(phn);
        return rootView;
    }

    void getproduct(String phn) {

        String url = "https://api.androidhive.info/json/movies.json?phn=" + phn;

        pDialog = new ProgressDialog(getContext());
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
                                Mproduct product = new Mproduct();
                                product.setName(obj.getString("title"));
                                product.setThumbnailUrl(obj.getString("image"));
                                product.setPrice(obj.get("rating") + "");
                                product.setCat(obj.getString("title"));
                                product.setDesc(obj.get("rating") + "");
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
                Intent i = new Intent(getContext(), ProductDetailActivity.class);
                Mproduct mproduct = (Mproduct) parent.getAdapter().getItem(position);
                i.putExtra("product", new String[]{
                        mproduct.getName(),
                        mproduct.getPrice(),
                        mproduct.getDesc(),
                        mproduct.getThumbnailUrl()});
                startActivity(i);
            }
        });

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
