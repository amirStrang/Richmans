package com.example.asus.richmans;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.relex.circleindicator.CircleIndicator;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductDetailActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager viewPager;
    SlideShowAdapter slideShowAdapter;
    CircleIndicator indicator;
    TextView txtName, txtPrice, txtDescription;
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
                showDialog();
            }
        });
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.activity_dialog);
        dialog.setCancelable(false);
        dialog.show();

        TextView txtMessage = (TextView) dialog.findViewById(R.id.txt_message);
        Button btnOk = (Button) dialog.findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        slideShowAdapter = new SlideShowAdapter(this,
                getIntent().getStringArrayExtra("product")[3],
                getIntent().getStringArrayExtra("product")[3],
                getIntent().getStringArrayExtra("product")[3]);
        viewPager.setAdapter(slideShowAdapter);

        indicator = (CircleIndicator) findViewById(R.id.circleIndicator);
        indicator.setViewPager(viewPager);

        txtName = (TextView) findViewById(R.id.txt_name);
        txtName.setText(getIntent().getStringArrayExtra("product")[0]);
        txtPrice = (TextView) findViewById(R.id.txt_product_price);
        txtPrice.setText(getIntent().getStringArrayExtra("product")[1]);
        txtDescription = (TextView) findViewById(R.id.txt_description);
        txtDescription.setText(getIntent().getStringArrayExtra("product")[2]);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        etNumber = (EditText) findViewById(R.id.et_number);

    }
}
