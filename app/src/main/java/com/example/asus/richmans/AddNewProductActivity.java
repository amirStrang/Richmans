package com.example.asus.richmans;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class AddNewProductActivity extends AppCompatActivity {

    Button btnSubmitProduct;
    EditText etName, etDescription;
    Spinner spinnerCat, spinnerSubCat;
    ImageView img1, img2, img3;
    TextView txtSubCat;

    String[] cats = {
            "کالای دیجیتال",
            "مد و پوشاک",
            "خانه، آشپزخانه و ابزار",
            "آرایشی و بهداشتی",
            "کتاب، فرهنگ و هنر",
            "ورزش و سفر",
            "مادر و کودک",
            "وسایل نقلیه و صنعتی",
            "خدمات"
    };

    String[] subCats0 = {
            "موبایل",
            "تبلت و کتابخوان",
            "لپ تاپ",
            "دوربین",
            "کامپیوتر و تجهیزات جانبی",
            "ماشین های اداری",
            "لوازم جانبی کالای دیجیتال"
    };
    String[] subCats1 = {
            "مردانه",
            "زنانه",
            "بچگانه",
            "ورزشی",
            "عطر",
            "ساعت",
            "اکسسوری لوازم شخصی"
    };
    String[] subCats2 = {
            "صوتی و تصویری",
            "لوازم خانگی برقی",
            "آشپزخانه",
            "سرو و پذیرایی",
            "دکوراتیو",
            "خواب حمام",
            "شستشو و نظافت",
            "ابزار غیر برقی",
            "ابزار برقی",
            "باغبانی",
            "نور و روشنایی"
    };
    String[] subCats3 = {
            "لوازم آرایشی",
            "لوازم بهداشتی",
            "لوازم شخصی برقی",
            "عینک آفتابی",
            "زیورآلات",
            "ابزار سلامت"
    };
    String[] subCats4 = {
            "کتاب و مجلات",
            "لوازم التحریر",
            "صنایع دستی",
            "فرش",
            "آلات موسیسقی",
            "فیلم",
            "نرم افزار و بازی",
            "محتوای آموزشی"
    };
    String[] subCats5 = {
            "پوشاک ورزشی",
            "کفش ورزشی",
            "لوازم ورزشی",
            "دوچرخه و لوازم جانبی",
            "تجهیزات سفر",
            "اسباب سفر",
            "حیوانات خانگی"
    };
    String[] subCats6 = {
            "ایمنی و مراقبت",
            "غذاخوری",
            "لوازم شخصی",
            "بهداشت و حمام",
            "گردش و سفر",
            "سرگرمی و آموزشی",
            "خواب کودک"
    };
    String[] subCats7 = {
            "خودرو",
            "لوازم جانبی خودرو",
            "لوازم مصرفی خودرو",
            "موتور سیکلت",
            "لوازم جانبی موتور سیکلت",
            "لوازم مصرفی موتور سیکلت",
            "انبارداری صنعتی"
    };
    String[] subCats8 = {
            "دندانپزشکی",
            "باشگاه",
            "کلاس های آزاد",
            "هتل",
            "تور مسافرتی",
            "کنسرت"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);


        init();

        spinnerCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            ArrayAdapter<String> subCatAdapter;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        subCatAdapter = new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_spinner_item, subCats0);
                        break;

                    case 1:
                        subCatAdapter = new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_spinner_item, subCats1);
                        break;

                    case 2:
                        subCatAdapter = new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_spinner_item, subCats2);
                        break;

                    case 3:
                        subCatAdapter = new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_spinner_item, subCats3);
                        break;

                    case 4:
                        subCatAdapter = new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_spinner_item, subCats4);
                        break;

                    case 5:
                        subCatAdapter = new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_spinner_item, subCats5);
                        break;

                    case 6:
                        subCatAdapter = new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_spinner_item, subCats6);
                        break;

                    case 7:
                        subCatAdapter = new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_spinner_item, subCats7);
                        break;

                    case 8:
                        subCatAdapter = new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_spinner_item, subCats8);
                        break;
                }

                subCatAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSubCat.setAdapter(subCatAdapter);

                spinnerSubCat.setVisibility(View.VISIBLE);
                txtSubCat.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ///
            }
        });

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnSubmitProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void init() {
        etName = (EditText) findViewById(R.id.et_name);
        etDescription = (EditText) findViewById(R.id.et_description);
        btnSubmitProduct = (Button) findViewById(R.id.btn_submit_product);
        spinnerCat = (Spinner) findViewById(R.id.spinner_cat);
        spinnerSubCat = (Spinner) findViewById(R.id.spinner_sub_cat);
        img1 = (ImageView) findViewById(R.id.img_p1);
        img2 = (ImageView) findViewById(R.id.img_p2);
        img3 = (ImageView) findViewById(R.id.img_p3);
        txtSubCat = (TextView) findViewById(R.id.txt_sub_cat);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cats);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(adapter);
    }
}
