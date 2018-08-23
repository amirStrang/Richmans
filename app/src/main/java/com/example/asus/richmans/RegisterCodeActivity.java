package com.example.asus.richmans;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.asus.richmans.app.AppController;
import com.example.asus.richmans.model.Mproduct;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterCodeActivity extends AppCompatActivity {

    EditText etCode;
    TextInputLayout etLayoutCode;
    Button btnRegister;
    TextView txtWrongPhone, txtNotGetCode;
    RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_code);
        init();

        mainLayout.setOnClickListener(null);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCode.getText().toString().equals("")) {
                    etCode.setError("کد را وارد کنید");
                    return;
                }
                save(etCode.getText().toString());
            }
        });
    }

    private void init() {
        etCode = (EditText) findViewById(R.id.et_code);
        etLayoutCode = (TextInputLayout) findViewById(R.id.et_layout_code);
        btnRegister = (Button) findViewById(R.id.btn_register);
        mainLayout = (RelativeLayout) findViewById(R.id.register_activity_layout);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void save(String code) {
        Send("http://89.163.249.183:3000/singupemail/" + code);
    }

    private ProgressDialog pDialog;

    void Send(final String URL) {

        pDialog = new ProgressDialog(RegisterCodeActivity.this);
        // Showing progress dialog before making http request
        pDialog.setMessage("منتظر تایید بمانید");
        pDialog.show();

        // Tag used to cancel the request
        String tag_string_req = "req_login";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("tag", "Login Response: " + response.toString());
                pDialog.dismiss();
                try {
                    JSONObject jObj = new JSONObject(response);

                    if (response.contains("id")) {

                        String code = jObj.getString("id");
                        tran(code);
//                        finish();
                    } else {
                        // Error in login. Get the error message
                        Toast.makeText(getApplicationContext(),
                                "کد وارد شده اشتباه است", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("tag", "Login Error: " + error.getMessage());
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        hidePDialog();
        super.onDestroy();
    }

    void tt(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    void tran(String phn) {
        hidePDialog();
        SaveMe(phn);
        SaveAccess();
        Intent i = new Intent(RegisterCodeActivity.this, HomePageActivity.class);
//        i.putExtra("phn", phn);
        startActivity(i);
        this.finish();
    }

    void SaveMe(String user) {

        File root = getFilesDir();
        File dir = new File(root.getAbsolutePath() + "/.richmans");
        dir.mkdirs();
        File file = new File(dir, "phn.txt");

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(user);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //tt(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            //tt(e.getMessage());
        }

    }

    void SaveAccess() {

        File root = getFilesDir();
        File dir = new File(root.getAbsolutePath() + "/.richmans");
        dir.mkdirs();
        File file = new File(dir, "acc.txt");

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println("BRONZE");
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //tt(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            //tt(e.getMessage());
        }

    }
}
