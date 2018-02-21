package com.example.asus.richmans;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;

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


    int pic = 1;

    String pic_path1 = "";
    String pic_path2 = "";
    String pic_path3 = "";

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

                pic = 1;
                pick();

            }
        });
        img2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                pic = 2;
                pick();

            }
        });
        img3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                pic = 3;
                pick();

            }
        });

        btnSubmitProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    /*
     sabt = (Button) findViewById(R.id.m_sabt);
		sabt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				 if (pic_path1 == "" || pic_path2 == "" || pic_path3 == "") {
				 tt("داشتن سه عکس الزامی است");
				 return;
				 }




postParam.put("par32", s_etfa_fire);


				try {
					BitmapFactory.Options options = new BitmapFactory.Options();

					options.inPreferredConfig = Bitmap.Config.RGB_565;
					options.inDither = true;
					options.inSampleSize = 8;

					Bitmap bm1 = BitmapFactory.decodeFile(pic_path1, options);
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					bm1.compress(Bitmap.CompressFormat.JPEG, 100, baos);
					byte[] b = baos.toByteArray();

					String encodedImage1 = Base64.encodeToString(b,
							Base64.DEFAULT);
					// tt(encodedImage1.length()+"");

					postParam.put("par34", encodedImage1);

					Bitmap bm2 = BitmapFactory.decodeFile(pic_path2, options);
					ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
					bm2.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
					byte[] b2 = baos2.toByteArray();

					String encodedImage2 = Base64.encodeToString(b2,
							Base64.DEFAULT);
					postParam.put("par35", encodedImage2);

					Bitmap bm3 = BitmapFactory.decodeFile(pic_path3, options);
					ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
					bm3.compress(Bitmap.CompressFormat.JPEG, 100, baos3);
					byte[] b3 = baos3.toByteArray();

					String encodedImage3 = Base64.encodeToString(b3,
							Base64.DEFAULT);
					postParam.put("par36", encodedImage3);
				} catch (Exception e) {
					//
				}

				// permission web and phone data for get phnumber here
				String line_number = "Line Number : "
						+ telManager.getLine1Number();

				postParam.put("par33", line_number);







*/

    void pick() {

        final CharSequence[] options = {"دوربین", "گالری"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AddNewProductActivity.this);

        builder.setTitle("Select Photo");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("دوربین"))

                {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (Build.VERSION.SDK_INT >= 24) {
                        try {
                            Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                            m.invoke(null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    File f = new File(android.os.Environment
                            .getExternalStorageDirectory(), "temp" + pic
                            + ".jpg");

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));

                    startActivityForResult(intent, 1);

                } else if (options[item].equals("گالری")) {

                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, 2);

                }

            }

        });

        builder.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {

                try {

                    String picturePath = Environment
                            .getExternalStorageDirectory().getAbsolutePath()
                            + File.separator + "temp" + pic + ".jpg";

                    Bitmap thumbnail = BitmapFactory.decodeFile(picturePath);

                    if (pic == 1) {
                        img1.setImageBitmap(thumbnail);
                        pic_path1 = picturePath;
                    } else if (pic == 2) {
                        img2.setImageBitmap(thumbnail);
                        pic_path2 = picturePath;
                    } else if (pic == 3) {
                        img3.setImageBitmap(thumbnail);
                        pic_path3 = picturePath;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();

                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor c = getContentResolver().query(selectedImage, filePath,
                        null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                String picturePath = c.getString(columnIndex);

                c.close();

                Bitmap thumbnail;
                try {
                    thumbnail = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));

                    if (pic == 1) {
                        img1.setImageBitmap(thumbnail);
                        pic_path1 = picturePath;
                    } else if (pic == 2) {
                        img2.setImageBitmap(thumbnail);
                        pic_path2 = picturePath;
                    } else if (pic == 3) {
                        img3.setImageBitmap(thumbnail);
                        pic_path3 = picturePath;
                    }
                } catch (Exception e) {
                    //
                }

            }
        }
    }

}
