package com.example.asus.richmans;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TextTutorialActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtTitle, txtExplain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_tutorial);

        init();

        txtTitle.setText(getIntent().getStringExtra("name"));
        txtExplain.setText(getIntent().getStringExtra("explain"));
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtExplain = (TextView) findViewById(R.id.txt_explain);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
