package com.example.asus.richmans;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

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
                if (etCode.getText().toString().equals("") || etCode.getText().toString().length() != 5) {
                    etCode.setError("کد را صحیح وارد کنید");
                    return;
                }
                save(etCode.getText().toString(), getIntent().getStringExtra("phn"));
            }
        });
    }

    private void init() {
        etCode = (EditText) findViewById(R.id.et_code);
        etLayoutCode = (TextInputLayout) findViewById(R.id.et_layout_code);
        btnRegister = (Button) findViewById(R.id.btn_regiser);
        mainLayout = (RelativeLayout) findViewById(R.id.register_activity_layout);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void save(String code, String phn) {
        Send("http://178.32.129.19:8075/api/Identify", phn, code);
    }

    private ProgressDialog pDialog;

    void Send(final String URL, final String phn, final String code) {
        Log.d("req", "___send started");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.show();

        final Map<String, String> postParam = new HashMap<String, String>();

        postParam.put("PHN", phn);
        postParam.put("CODE", code);


        ////////////////////////////////////////////////////////

        final Thread send = new Thread(new Runnable() {
            @Override
            public void run() {

                JSONObject obj = new JSONObject(postParam);

                postData(URL, obj, phn);

            }
        });

        send.start();
    }

    public void postData(String url, JSONObject obj, final String phn) {
        // Create a new HttpClient and Post Header
        HttpParams myParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(myParams, 10000);
        HttpConnectionParams.setSoTimeout(myParams, 10000);
        HttpClient httpclient = new DefaultHttpClient(myParams);

        try {
            HttpPost httppost = new HttpPost(url.toString());
            httppost.setHeader("Content-type", "application/json");

            StringEntity se = new StringEntity(obj.toString());
            se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httppost.setEntity(se);

            HttpResponse response = httpclient.execute(httppost);
            final String temp = EntityUtils.toString(response.getEntity());

            //handle temp
            if (!temp.equals("1")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                        tt("خطا در ارسال داده");

                        //////////////sample
//                        tran(phn);
                        //////////////sample
                    }
                });
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tran(phn);
                    }
                });
            }
        } catch (ClientProtocolException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //
                }
            });

        } catch (IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //
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

    @Override
    protected void onDestroy() {
        hidePDialog();
        super.onDestroy();
    }

    void tt(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    void tran(String phn) {
        hidePDialog();
        SaveMe(phn);
        SaveAccess();
        Intent i = new Intent(RegisterCodeActivity.this, HomePageActivity.class);
        i.putExtra("phn", phn);
        startActivity(i);
        this.finish();
    }

    void SaveMe(String user) {

        File root = getFilesDir();
        File dir = new File(root.getAbsolutePath() + "/.richmans");
        dir.mkdirs();
        File file = new File(dir, "phn.txt");

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(user);
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //tt(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            //tt(e.getMessage());
        }

    }

    void SaveAccess() {

        File root = getFilesDir();
        File dir = new File(root.getAbsolutePath() + "/.richmans");
        dir.mkdirs();
        File file = new File(dir, "acc.txt");

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println("BRONZE");
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //tt(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            //tt(e.getMessage());
        }

    }
}
