package com.example.asus.richmans;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout btnLearn, btnStore, btnMyStore, btnHistory, btnAboutUs, btnAboutGame, btnContactUs, btnSetting;
    TextView txtCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        init();

    }

    void init() {
        btnAboutGame = (RelativeLayout) findViewById(R.id.btn_about_game);
        btnAboutUs = (RelativeLayout) findViewById(R.id.btn_about_us);
        btnContactUs = (RelativeLayout) findViewById(R.id.btn_contact_us);
        btnHistory = (RelativeLayout) findViewById(R.id.btn_history);
        btnLearn = (RelativeLayout) findViewById(R.id.btn_learn);
        btnMyStore = (RelativeLayout) findViewById(R.id.btn_my_store);
        btnSetting = (RelativeLayout) findViewById(R.id.btn_setting);
        btnStore = (RelativeLayout) findViewById(R.id.btn_store);

        txtCredit = (TextView) findViewById(R.id.txtCredit);

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

                break;
            case R.id.btn_about_game:

                break;
            case R.id.btn_contact_us:

                break;
            case R.id.btn_history:

                break;
            case R.id.btn_learn:

                break;
            case R.id.btn_my_store:

                break;
            case R.id.btn_setting:

                break;
            case R.id.btn_store:
                i = new Intent(HomePageActivity.this, StoreActivity.class);
                startActivity(i);
                this.finish();
                break;
        }
    }
}
