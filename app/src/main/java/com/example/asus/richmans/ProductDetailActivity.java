package com.example.asus.richmans;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
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

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.relex.circleindicator.CircleIndicator;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    SlideShowAdapter slideShowAdapter;
    CircleIndicator indicator;
    TextView txtName, txtPrice, txtDescription, txtTotalPrice;
    Button btnBuy;
    EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        init();

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buy
                String str = readFileAsString(getBaseContext(), getFilesDir().getAbsolutePath() + "/.richmans/phn.txt");
                Send("http://ahmadiTest.sepantahost.com/api/BuyM",
                        getIntent().getStringArrayExtra("product")[6],
                        str,
                        getIntent().getStringArrayExtra("product")[7],
                        txtTotalPrice.getText().toString()
                );
            }
        });
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

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    int price;

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        slideShowAdapter = new SlideShowAdapter(this,
                getIntent().getStringArrayExtra("product")[3],
                getIntent().getStringArrayExtra("product")[4],
                getIntent().getStringArrayExtra("product")[5]);
        viewPager.setAdapter(slideShowAdapter);

        indicator = (CircleIndicator) findViewById(R.id.circleIndicator);
        indicator.setViewPager(viewPager);

        price = Integer.parseInt(getIntent().getStringArrayExtra("product")[1]);
        txtName = (TextView) findViewById(R.id.txt_name);
        txtName.setText(getIntent().getStringArrayExtra("product")[0]);
        txtPrice = (TextView) findViewById(R.id.txt_product_price);
        txtPrice.setText(getIntent().getStringArrayExtra("product")[1]);
        txtDescription = (TextView) findViewById(R.id.txt_description);
        txtDescription.setText(getIntent().getStringArrayExtra("product")[2]);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        txtTotalPrice = (TextView) findViewById(R.id.txt_product_total_price);
        txtTotalPrice.setText(getIntent().getStringArrayExtra("product")[1]);
        etNumber = (EditText) findViewById(R.id.et_number);
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etNumber.getText().toString().equals("")) txtTotalPrice.setText("0");
                else {
                    int tp = Integer.parseInt(etNumber.getText().toString()) * price;
//                            Integer.parseInt(txtPrice.getText().toString());
                    txtTotalPrice.setText(tp + "");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private ProgressDialog pDialog;

    void Send(final String URL, String ID, String PHN, String STORE, String TP) {
        Log.d("req", "___send started");
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.show();

        final Map<String, String> postParam = new HashMap<String, String>();

        postParam.put("ID", ID);
        postParam.put("PHN", PHN);
        postParam.put("Store", STORE);
        postParam.put("TP", TP);

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

            StringEntity se = new StringEntity(obj.toString(), HTTP.UTF_8);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);
            String temp = EntityUtils.toString(response.getEntity());
//            temp = temp.substring(1, temp.length() - 1);

            if (temp.equals("1")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                        tt("با موفقیت انجام شد");
                        ProductDetailActivity.this.finish();
                    }
                });
            } else if (temp.equals("0")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                        tt("موجودی برای خرید کافی نیست");
                    }
                });
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                        tt("خطا در ارسال داده\nدوباره امتحان کنید");
                    }
                });
            }

        } catch (ClientProtocolException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hidePDialog();
                    tt("خطا در ارسال داده\nدوباره امتحان کنید");
                }
            });

        } catch (IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hidePDialog();
                    tt("خطا در ارسال داده\nدوباره امتحان کنید");
                }
            });
        }


    }

    void tt(String str) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
}
