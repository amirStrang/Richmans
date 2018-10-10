package com.example.asus.richmans;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SetBaseMoneyActivity extends AppCompatActivity {

    EditText etBaseMoney;
    Button btnStart;
    TextView tvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_base_money);

        init();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etBaseMoney.getText().toString().equals("") || etBaseMoney.getText().toString().length() > 10) {
                    etBaseMoney.setError("کد را صحیح وارد کنید");
                    return;
                }
                setBase(etBaseMoney.getText().toString(), getIntent().getStringExtra("phn"));
            }
        });

        etBaseMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvv.setText("مبلغ پایه مورد نظر شما"+"\n"+Separate3digits(etBaseMoney.getText().toString())+"\n"+"تومان");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private String Separate3digits(String value) {
        char[] temp = value.toCharArray();
        String result = "";
        int counter = 0;
        for (int i = value.length() - 1; i >= 0; i--) {
            result += temp[i];
            counter++;
            if (counter % 3 == 0 && i != 0) {
                result += ",";
            }
        }
        temp = result.toCharArray();
        result = "";
        for (int i = temp.length - 1; i >= 0; i--) {
            result += temp[i];
        }
        return result;
    }

    private void init() {
        etBaseMoney = (EditText) findViewById(R.id.et_base_money);
        btnStart = (Button) findViewById(R.id.btn_start);
        tvv=(TextView)findViewById(R.id.tvv);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void setBase(String bMoney, String phn) {
        Send("http://madresetavangari.ir/startgame", phn, bMoney);
    }

    private ProgressDialog pDialog;

    void Send(final String URL, final String phn, final String bMoney) {
        Log.d("req", "___send started");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.show();

        final Map<String, String> postParam = new HashMap<String, String>();

        postParam.put("id", phn);
        postParam.put("base", bMoney);


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
//            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            se.setContentType("application/json");
            httppost.setEntity(se);


            HttpResponse response = httpclient.execute(httppost);
            String temp = EntityUtils.toString(response.getEntity());
            if (!temp.contains("ok")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                        tt("خطا در ارسال داده");

                        //////////////sample
//                        tran();
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
                    //
                }
            });

        } catch (IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //
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
//        Intent i = new Intent(SetBaseMoneyActivity.this, HomePageActivity.class);
//        startActivity(i);
        this.finish();
    }

}
