package com.example.asus.richmans;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

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
                Intent i = new Intent(GetPhoneActivity.this, RegisterCodeActivity.class);
                startActivity(i);
                GetPhoneActivity.this.finish();
                //sent mesage
            }
        });
    }

    void init() {
        etPhone = (EditText) findViewById(R.id.et_phone);
        etLayoutPhone = (TextInputLayout) findViewById(R.id.et_layout_code);
        btnSendCode = (Button) findViewById(R.id.btn_regiser);
        mainLayout = (RelativeLayout) findViewById(R.id.get_phone_activity_layout);
    }
}
