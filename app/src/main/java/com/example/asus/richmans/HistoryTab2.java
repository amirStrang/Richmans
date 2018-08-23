package com.example.asus.richmans;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.asus.richmans.adapter.HistoryCustomListAdapter;
import com.example.asus.richmans.app.AppController;
import com.example.asus.richmans.model.History;

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

public class HistoryTab2 extends Fragment {

    private static final String TAG = "Tab2";

    private ProgressDialog pDialog;
    private List<History> historyList = new ArrayList<History>();
    private ListView listView;
    private HistoryCustomListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_history_tab2, container, false);

        listView = (ListView) rootView.findViewById(R.id.list);
        adapter = new HistoryCustomListAdapter(getActivity(), historyList);
        listView.setAdapter(adapter);

        ///////////////////////////////// get phn
        String phn = readFileAsString(getContext(),
                getContext().getFilesDir().getAbsolutePath() + "/.richmans/phn.txt");
        getHistory(phn);

        return rootView;
    }

//    void getHistory(String phn) {
//
//        String url = "http://89.163.249.183:3000" + phn;
//
//        pDialog = new ProgressDialog(getContext());
//        pDialog.setMessage("لطفا صبر کنید");
//        pDialog.show();
//
//        JsonArrayRequest productReq = new JsonArrayRequest(url,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.d(TAG, response.toString());
//                        hidePDialog();
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject obj = response.getJSONObject(i);
//                                History history = new History();
//                                history.setFrom(obj.get("releaseYear") + "/" + obj.get("rating") + "/" + obj.get("rating"));
//                                history.setTo(obj.get("releaseYear") + "/" + obj.get("rating") + "/" + obj.get("rating"));
//                                history.setDay(obj.get("rating") + "");
//                                historyList.add(history);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        adapter.notifyDataSetChanged();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                hidePDialog();
//            }
//        });
//
//        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(productReq);
//
//    }

    private void getHistory(final String phn) {
        // Tag used to cancel the request
        String tag_string_req = "req_get_history";

        String url = "http://89.163.249.183:3000/gethistory";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("tag", "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    String status = jObj.getString("status");
                    if (!status.equals("ok")) {
                        return;
                    }
                    JSONArray obj = jObj.getJSONArray("doreha");
                    for (int i = 0; i < obj.length(); i++) {
                        try {
                            JSONObject tobj = obj.getJSONObject(i);
                            History history = new History();
                            history.setFrom(tobj.getString("startTime"));
                            history.setTo(tobj.getString("endTime"));
                            history.setDay(tobj.getString("day"));
                            historyList.add(history);
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
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("tag", "Login Error: " + error.getMessage());
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();

                params.put("id", phn);
//                params.put("password", password);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
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
