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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class HistoryTab1 extends Fragment {


    private static final String TAG = "Tab1";

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

    private void getproduct(String phn) {
        String tag_string_req = "req_get_history";
        String url = "http://seyyedmahdi.eu-4.evennode.com/getmyshop/" + phn;

        pDialog = new ProgressDialog(getContext());
        // Showing progress dialog before making http request
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    JSONArray aObj = jObj.getJSONArray("myshop");
                    hidePDialog();
                    for (int i = 0; i < aObj.length(); i++) {
                        try {
                            JSONObject obj = aObj.getJSONObject(i);
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
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                }
            }
        }
                , new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("tag", "Login Error: " + error.getMessage());
            }
        });

        // Adding request to request queue
        AppController.getInstance().

                addToRequestQueue(strReq, tag_string_req);

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