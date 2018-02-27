package com.example.asus.richmans;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
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
    }

    private void init() {
    }
}
