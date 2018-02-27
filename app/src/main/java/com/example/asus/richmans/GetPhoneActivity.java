package com.example.asus.richmans;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

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
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GetPhoneActivity extends AppCompatActivity {

    EditText etPhone;
    TextInputLayout etLayoutPhone;
    Button btnSendCode;
    RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_phone);

        init();

        mainLayout.setOnClickListener(null);
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPhone.getText().toString().equals("") || etPhone.getText().toString().length() != 11) {
                    etPhone.setError("شماره تلفن را صحیح وارد کنید");
                    return;
                }
                reg(etPhone.getText().toString());
            }
        });
    }

    void init() {
        etPhone = (EditText) findViewById(R.id.et_phone);
        etLayoutPhone = (TextInputLayout) findViewById(R.id.et_layout_code);
        btnSendCode = (Button) findViewById(R.id.btn_regiser);
        mainLayout = (RelativeLayout) findViewById(R.id.get_phone_activity_layout);
    }

    void reg(String phn) {
        Send("http://ahmaditest.sepantahost.com/api/", phn);
    }

    private ProgressDialog pDialog;

    void Send(final String URL, String phn) {
        Log.d("req", "___send started");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.show();

        final Map<String, String> postParam = new HashMap<String, String>();

        postParam.put("PHN", phn);


        ////////////////////////////////////////////////////////

        final Thread send = new Thread(new Runnable() {
            @Override
            public void run() {

                JSONObject obj = new JSONObject(postParam);

                postData(URL, obj);

            }
        });

        send.start();
    }

    public void postData(String url, JSONObject obj) {
        // Create a new HttpClient and Post Header
        HttpParams myParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(myParams, 10000);
        HttpConnectionParams.setSoTimeout(myParams, 10000);
        HttpClient httpclient = new DefaultHttpClient(myParams);

        try {
            HttpPost httppost = new HttpPost(url.toString());
            httppost.setHeader("Content-type", "application/json");

            StringEntity se = new StringEntity(obj.toString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);
            final String temp = EntityUtils.toString(response.getEntity());

            //handle temp
            if (!temp.equals(1)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
               /*
                        hidePDialog();
                        tt("خطا در ارسال داده");
                */
                        //////////////sample
                        tran();
                        //////////////sample
                    }
                });
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tran();
                    }
                });
            }


        } catch (ClientProtocolException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });

        } catch (IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });
        }


    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    void tt(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    void tran() {
        Intent i = new Intent(GetPhoneActivity.this, RegisterCodeActivity.class);
        startActivity(i);
        this.finish();
    }
}
