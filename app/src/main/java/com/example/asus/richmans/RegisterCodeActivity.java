package com.example.asus.richmans;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterCodeActivity.this, HomePageActivity.class));
            }
        });
    }

    private void init() {
        btnRegister = (Button) findViewById(R.id.btn_regiser);
    }
}
