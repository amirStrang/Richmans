package com.example.asus.richmans;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SetBaseMoneyActivity extends AppCompatActivity {

    EditText etBaseMoney;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_base_money);

        init();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                Intent i = new Intent(SetBaseMoneyActivity.this, HomePageActivity.class);
                startActivity(i);
                SetBaseMoneyActivity.this.finish();
            }
        });
    }

    private void init() {
        etBaseMoney = (EditText) findViewById(R.id.et_base_money);
        btnStart = (Button) findViewById(R.id.btn_start);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
