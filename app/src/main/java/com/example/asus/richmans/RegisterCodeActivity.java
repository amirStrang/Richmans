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

        mainLayout.setOnClickListener(null);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checking
                Intent i = new Intent(RegisterCodeActivity.this, HomePageActivity.class);
                startActivity(i);
                RegisterCodeActivity.this.finish();
            }
        });
    }

    private void init() {
        etCode = (EditText) findViewById(R.id.et_code);
        etLayoutCode = (TextInputLayout) findViewById(R.id.et_layout_code);
        btnRegister = (Button) findViewById(R.id.btn_regiser);
        mainLayout = (RelativeLayout) findViewById(R.id.register_activity_layout);
    }
}
