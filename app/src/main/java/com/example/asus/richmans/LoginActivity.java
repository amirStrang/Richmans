package com.example.asus.richmans;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    //login page :
    RelativeLayout loginLayout;
    EditText etUserName, etPass;
    TextInputLayout etlayoutUserName, etlayoutPass;
    Button btnLogin;
    TextView tvForgottenPass;
    RelativeLayout btnGoToRegister;

    //register page :
    RelativeLayout registerLayout;
    ImageView imgClose;
    EditText etUserNameReg, etPassReg, etConfPassReg;
    TextInputLayout etlayoutUserNameReg, etlayoutPassReg, etlayoutConfPassReg;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        loginLayout.setOnClickListener(null);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUserName.getText().toString().equals("")) {
                    etUserName.setError("لطفا نام کاربری خود را وارد کنید");
                    return;
                }
                reg(etUserName.getText().toString());
            }
        });

        btnGoToRegister.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                final float defaultX = btnGoToRegister.getX();
                final float defaultY = btnGoToRegister.getY();

//              ValueAnimator animBtn = v.animate().translationXBy(-100);
                ValueAnimator animBtn = ValueAnimator.ofFloat(0f, -100f);
                animBtn.setDuration(200);
                animBtn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        btnGoToRegister.setTranslationX((float) animation.getAnimatedValue());
                    }
                });

                int x = (int) registerLayout.getWidth();
                int y = (int) registerLayout.getY() + 60;

                int startRadius = 0;
                int endRadius = (int) Math.hypot(loginLayout.getWidth(), loginLayout.getHeight());

                final Animator animLayout = ViewAnimationUtils.createCircularReveal(registerLayout, x, y, startRadius, endRadius);

                animBtn.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        animLayout.setDuration(500).start();
                        registerLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        loginLayout.setVisibility(View.INVISIBLE);
                        btnGoToRegister.setVisibility(View.INVISIBLE);
                        btnGoToRegister.setX(defaultX);
                        btnGoToRegister.setY(defaultY);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                animBtn.start();
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                int x = (int) registerLayout.getWidth();
                int y = (int) registerLayout.getY() + 60;

                int startRadius = Math.max(loginLayout.getWidth(), loginLayout.getHeight());
                int endRadius = 0;

                Animator anim = ViewAnimationUtils.createCircularReveal(registerLayout, x, y, startRadius, endRadius);
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                        loginLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        registerLayout.setVisibility(View.GONE);
                        btnGoToRegister.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                anim.setDuration(500).start();

            }
        });
    }

    void init() {
        //login page :
        loginLayout = (RelativeLayout) findViewById(R.id.login_layout);
        etUserName = (EditText) findViewById(R.id.et_username);
        etPass = (EditText) findViewById(R.id.et_password);
        etlayoutUserName = (TextInputLayout) findViewById(R.id.et_layout_username);
        etlayoutPass = (TextInputLayout) findViewById(R.id.et_layout_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvForgottenPass = (TextView) findViewById(R.id.tv_forgotPass);
        btnGoToRegister = (RelativeLayout) findViewById(R.id.btn_go_to_register);

        //register page :
        registerLayout = (RelativeLayout) findViewById(R.id.register_layout);
        imgClose = (ImageView) findViewById(R.id.img_close);
        etUserNameReg = (EditText) findViewById(R.id.et_username_r);
        etPassReg = (EditText) findViewById(R.id.et_password_r);
        etConfPassReg = (EditText) findViewById(R.id.et_confirm_password_r);
        etlayoutUserNameReg = (TextInputLayout) findViewById(R.id.et_layout_username_r);
        etlayoutPassReg = (TextInputLayout) findViewById(R.id.et_layout_password_r);
        etlayoutConfPassReg = (TextInputLayout) findViewById(R.id.et_layout_confirm_password_r);
        btnReg = (Button) findViewById(R.id.btn_register);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void reg(String phn) {
        Send("http://seyyedmahdi.eu-4.evennode.com/singup", phn);
    }

    private ProgressDialog pDialog;

    void Send(final String URL, final String phn) {
        Log.d("req", "___send started");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.show();

        final Map<String, String> postParam = new HashMap<String, String>();

        postParam.put("email", phn);


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
            String temp = EntityUtils.toString(response.getEntity());

            //handle temp
            if (temp.contains("currectly")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tt("ایمیل ثبت اولیه شد");
                        tran();
                    }
                });
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tt("خطا در ارسال داده");
                    }
                });
            }

        } catch (ClientProtocolException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });

        } catch (IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });
        }


    }

    @Override
    protected void onDestroy() {
        hidePDialog();
        super.onDestroy();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    void tt(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    void tran() {
        hidePDialog();
        Intent i = new Intent(LoginActivity.this, RegisterCodeActivity.class);
        startActivity(i);
        this.finish();
    }
}
