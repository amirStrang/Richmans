package com.example.asus.richmans;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class AddNewProductActivity extends AppCompatActivity {

    Button btnSubmitProduct;
    EditText etName, etDescription, etPrice;
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
            "نور و روشنایی",
            "خوراکی و آشامیدنی"
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
        etPrice = (EditText) findViewById(R.id.et_price);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cats);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCat.setAdapter(adapter);
        requestPermission();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private static final int REQUEST_WRITE_PERMISSION = 29843;

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
//            openFilePicker();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            openFilePicker();
        }
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
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CustomSpinnerAdapter subCatAdapter = new CustomSpinnerAdapter(getApplicationContext(), subCats0);

                switch (position) {
                    case 0:
                        subCatAdapter = new CustomSpinnerAdapter
                                (getApplicationContext(), subCats0);
                        break;

                    case 1:
                        subCatAdapter = new CustomSpinnerAdapter
                                (getApplicationContext(), subCats1);
                        break;

                    case 2:
                        subCatAdapter = new CustomSpinnerAdapter
                                (getApplicationContext(), subCats2);
                        break;

                    case 3:
                        subCatAdapter = new CustomSpinnerAdapter
                                (getApplicationContext(), subCats3);
                        break;

                    case 4:
                        subCatAdapter = new CustomSpinnerAdapter
                                (getApplicationContext(), subCats4);
                        break;

                    case 5:
                        subCatAdapter = new CustomSpinnerAdapter
                                (getApplicationContext(), subCats5);
                        break;

                    case 6:
                        subCatAdapter = new CustomSpinnerAdapter
                                (getApplicationContext(), subCats6);
                        break;

                    case 7:
                        subCatAdapter = new CustomSpinnerAdapter
                                (getApplicationContext(), subCats7);
                        break;

                    case 8:
                        subCatAdapter = new CustomSpinnerAdapter
                                (getApplicationContext(), subCats8);
                        break;
                }

                spinnerSubCat.setAdapter(subCatAdapter);
                spinnerSubCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    }
                });
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
                if (etName.getText().toString().equals("")) {
                    etName.setError("داشتن نام الزامی است");
                    return;
                }
                if (etDescription.getText().toString().equals("")) {
                    etDescription.setError("داشتن توضیحات الزامی است");
                    return;
                }
                if (etPrice.getText().toString().equals("")) {
                    etPrice.setError("داشتن قیمت الزامی است");
                    return;
                }


                if (pic_path1 == "" || pic_path2 == "" || pic_path3 == "") {
                    tt("داشتن سه عکس الزامی است");
                    return;
                }

                String phn = readFileAsString(getBaseContext(), getFilesDir().getAbsolutePath() + "/.richmans/phn.txt");
                //post
                Send("http://seyyedmahdi.eu-4.evennode.com/addtomyshop",
                        phn,
                        spinnerCat.getSelectedItem().toString(),
                        spinnerSubCat.getSelectedItem().toString(),
                        etName.getText().toString(),
                        etDescription.getText().toString(),
                        etPrice.getText().toString());

            }
        });
    }

    public String readFileAsString(Context context, String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(filePath)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);
        } catch (FileNotFoundException e) {
            //
        } catch (IOException e) {
            //
        }

        return stringBuilder.toString();
    }

    void tt(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    private ProgressDialog pDialog;

    void Send(final String URL, String ID, String cat, String subcat, String name, String Des, String price) {
        Log.d("req", "___send started");
        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.show();

        final Map<String, String> postParam = new HashMap<String, String>();

        postParam.put("id", ID);
        postParam.put("name", name);
        postParam.put("comment", Des);
        postParam.put("price", price);
//        postParam.put("CategoryName", cat);
        postParam.put("daste", subcat);


        //pics
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

            postParam.put("pic1", encodedImage1);

            Bitmap bm2 = BitmapFactory.decodeFile(pic_path2, options);
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            bm2.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
            byte[] b2 = baos2.toByteArray();

            String encodedImage2 = Base64.encodeToString(b2,
                    Base64.DEFAULT);
            postParam.put("pic2", encodedImage2);

            Bitmap bm3 = BitmapFactory.decodeFile(pic_path3, options);
            ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
            bm3.compress(Bitmap.CompressFormat.JPEG, 100, baos3);
            byte[] b3 = baos3.toByteArray();

            String encodedImage3 = Base64.encodeToString(b3,
                    Base64.DEFAULT);
            postParam.put("pic3", encodedImage3);
        } catch (Exception e) {
            Log.d("ppppppppiiiiiiiicccc", e.getMessage());
            tt("خطا در ارسال عکس");
        }


        ////////////////////////////////////////////////////////

        final Thread send = new Thread(new Runnable() {
            @Override
            public void run() {

                JSONObject obj = new JSONObject(postParam);

                postData(URL, obj);

            }
        });

        send.start();
    }

    public void postData(String url, JSONObject obj) {
        // Create a new HttpClient and Post Header
        HttpParams myParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(myParams, 10000);
        HttpConnectionParams.setSoTimeout(myParams, 10000);
        HttpClient httpclient = new DefaultHttpClient(myParams);

        try {
            HttpPost httppost = new HttpPost(url.toString());
            httppost.setHeader("Content-type", "application/json");

            StringEntity se = new StringEntity(obj.toString(), HTTP.UTF_8);
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);
            String temp = EntityUtils.toString(response.getEntity());
//            temp = temp.substring(1, temp.length() - 1);

            if (temp.contains("ok")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                        tt("با موفقیت ثبت شد");

                        AddNewProductActivity.this.finish();
                    }
                });
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                        tt("خطا در ارسال داده\nدوباره امتحان کنید");
                    }
                });
            }

        } catch (ClientProtocolException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hidePDialog();
                    tt("خطا در ارسال داده\nدوباره امتحان کنید");
                }
            });

        } catch (IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    hidePDialog();
                    tt("خطا در ارسال داده\nدوباره امتحان کنید");
                }
            });
        }

    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

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
