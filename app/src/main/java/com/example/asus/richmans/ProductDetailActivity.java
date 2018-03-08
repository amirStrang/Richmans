package com.example.asus.richmans;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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
    TextView txtName, txtPrice, txtDescription, txtTotalPrice;
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

    int price;

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        slideShowAdapter = new SlideShowAdapter(this,
                getIntent().getStringArrayExtra("product")[3],
                getIntent().getStringArrayExtra("product")[4],
                getIntent().getStringArrayExtra("product")[5]);
        viewPager.setAdapter(slideShowAdapter);

        indicator = (CircleIndicator) findViewById(R.id.circleIndicator);
        indicator.setViewPager(viewPager);

        price = Integer.parseInt(getIntent().getStringArrayExtra("product")[1]);
        txtName = (TextView) findViewById(R.id.txt_name);
        txtName.setText(getIntent().getStringArrayExtra("product")[0]);
        txtPrice = (TextView) findViewById(R.id.txt_product_price);
        txtPrice.setText(getIntent().getStringArrayExtra("product")[1]);
        txtDescription = (TextView) findViewById(R.id.txt_description);
        txtDescription.setText(getIntent().getStringArrayExtra("product")[2]);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        txtTotalPrice = (TextView) findViewById(R.id.txt_product_total_price);
        txtTotalPrice.setText(getIntent().getStringArrayExtra("product")[1]);
        etNumber = (EditText) findViewById(R.id.et_number);
        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etNumber.getText().toString().equals("")) txtTotalPrice.setText("0");
                else {
                    int tp = Integer.parseInt(etNumber.getText().toString()) * price;
//                            Integer.parseInt(txtPrice.getText().toString());
                    txtTotalPrice.setText(tp + "");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }
}
