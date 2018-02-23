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
}
