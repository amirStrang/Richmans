package com.example.asus.richmans;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.richmans.NotificationManager.time;

import org.json.JSONException;
import org.json.JSONObject;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RelativeLayout btnLearn, btnStore, btnMyStore, btnHistory, btnAboutUs, btnAboutGame, btnContactUs, btnSetting;
    TextView txtCredit;
    ProgressBar prbCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init();

        ntf();

        loadCredit("http://techiesatish.com/demo_api/spinner.php");

    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAboutGame = (RelativeLayout) findViewById(R.id.btn_about_game);
        btnAboutUs = (RelativeLayout) findViewById(R.id.btn_about_us);
        btnContactUs = (RelativeLayout) findViewById(R.id.btn_contact_us);
        btnHistory = (RelativeLayout) findViewById(R.id.btn_history);
        btnLearn = (RelativeLayout) findViewById(R.id.btn_learn);
        btnMyStore = (RelativeLayout) findViewById(R.id.btn_my_store);
        btnSetting = (RelativeLayout) findViewById(R.id.btn_reset);
        btnStore = (RelativeLayout) findViewById(R.id.btn_store);

        txtCredit = (TextView) findViewById(R.id.txtCredit);

        prbCredit = (ProgressBar) findViewById(R.id.prb_credit);

        btnAboutGame.setOnClickListener(this);
        btnAboutUs.setOnClickListener(this);
        btnContactUs.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnLearn.setOnClickListener(this);
        btnMyStore.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        btnStore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent i;

        switch (id) {
            case R.id.btn_about_us:
                i = new Intent(HomePageActivity.this, AboutUsActivity.class);
                startActivity(i);
                break;
            case R.id.btn_about_game:
                i = new Intent(HomePageActivity.this, AboutGameActivity.class);
                startActivity(i);
                break;
            case R.id.btn_contact_us:
                i = new Intent(HomePageActivity.this, ContactUsActivity.class);
                startActivity(i);
                break;
            case R.id.btn_history:
                i = new Intent(HomePageActivity.this, HistoryActivity.class);
                startActivity(i);
                break;
            case R.id.btn_learn:
                i = new Intent(HomePageActivity.this, LearnPageActivity.class);
                startActivity(i);
                break;
            case R.id.btn_reset:
                //reset credit and history changing
                txtCredit.setText("0");
                break;
            case R.id.btn_my_store:
                i = new Intent(HomePageActivity.this, MyShopActivity.class);
                startActivity(i);
                break;
            case R.id.btn_store:
                i = new Intent(HomePageActivity.this, StoreActivity.class);
                startActivity(i);
                break;
        }
    }

    void inc(final int from, final int to) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                txtCredit.setText(from + "");
                if (from < to)
                    inc(from + 1000, to);
            }
        }, 10);
    }

    private void loadCredit(String url) {

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    int prog = jsonObject.getInt("success") * 753000;

                    prbCredit.setVisibility(View.INVISIBLE);
                    txtCredit.setVisibility(View.VISIBLE);

                    inc(0, prog);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }

        });

        int socketTimeout = 30000;

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        requestQueue.add(stringRequest);

    }

    void ntf() {
        time.notify(getApplicationContext());
    }
}
