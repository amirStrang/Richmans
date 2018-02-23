package com.example.asus.richmans;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
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
    NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        init();

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //buy
            }
        });

        numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {
                //change total price
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
        slideShowAdapter = new SlideShowAdapter(this);
        viewPager.setAdapter(slideShowAdapter);

        indicator = (CircleIndicator) findViewById(R.id.circleIndicator);
        indicator.setViewPager(viewPager);

        txtName = (TextView) findViewById(R.id.txt_name);
        txtPrice = (TextView) findViewById(R.id.txt_price);
        txtDescription = (TextView) findViewById(R.id.txt_description);
        btnBuy = (Button) findViewById(R.id.btn_buy);
        numberPicker = (NumberPicker) findViewById(R.id.number_picker);

    }

    //initialize
//        txtName.setText();
//        txtPrice.setText();
//        txtDescription.setText();
}
