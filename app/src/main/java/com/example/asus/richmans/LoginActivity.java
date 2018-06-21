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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    //login page :
    RelativeLayout loginLayout;
    EditText etPhone, etPass;
    TextInputLayout etlayoutPhone, etlayoutPass;
    Button btnLogin;
    TextView tvForgottenPass;
    RelativeLayout btnGoToRegister;

    //register page :
    RelativeLayout registerLayout;
    ImageView imgClose;
    EditText etUserNameReg, etPassReg, etConfPassReg, etPhoneReg, etEmailReg;
    TextInputLayout etlayoutUserNameReg, etlayoutPassReg, etlayoutConfPassReg, etlayoutPhoneReg, etlayoutEmailReg;
    Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUserNameReg.getText().toString().equals("")) {
                    etUserNameReg.setError("لطفا نام کاربری خود را وارد کنید");
                    return;
                }
                if (etEmailReg.getText().toString().equals("")) {
                    etUserNameReg.setError("لطفا ایمیل خود را وارد کنید");
                    return;
                }
                if (etPhoneReg.getText().toString().equals("")) {
                    etUserNameReg.setError("لطفا شماره خود را وارد کنید");
                    return;
                }
                if (etPassReg.getText().toString().equals("")) {
                    etPassReg.setError("لطفا رمز خود را وارد کنید");
                    return;
                }
                if (etConfPassReg.getText().toString().equals("")) {
                    etConfPassReg.setError("لطفا رمز خود را تکرار کنید");
                    return;
                }
                if (!etConfPassReg.getText().toString().equals(etPassReg.getText().toString())) {
                    etConfPassReg.setError("رمز ها تطابق ندارد");
                    return;
                } else {
                    reg(etUserNameReg.getText().toString(), etPhoneReg.getText().toString()
                            , etPassReg.getText().toString(), etEmailReg.getText().toString());
                }
            }
        });

        loginLayout.setOnClickListener(null);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPhone.getText().toString().equals("")) {
                    etPhone.setError("لطفا نام کاربری خود را وارد کنید");
                    return;
                }
                if (etPass.getText().toString().equals("")) {
                    etPass.setError("لطفا رمز عبور خود را وارد کنید");
                    return;
                }
                log(etPhone.getText().toString(), etPass.getText().toString());
            }
        });

        tvForgottenPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPhone.getText().toString().equals("")) {
                    etPhone.setError("لطفا نام کاربری خود را وارد کنید");
                    return;
                }
                forgotten(etPhone.getText().toString());
            }
        });

        btnGoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= 21) {

                    final float defaultX = btnGoToRegister.getX();
                    final float defaultY = btnGoToRegister.getY();
                    ValueAnimator animBtn = ValueAnimator.ofFloat(0f, -100f);
                    animBtn.setDuration(200);
                    animBtn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        public void onAnimationUpdate(ValueAnimator animation) {
                            btnGoToRegister.setTranslationX((float) animation.getAnimatedValue());
                        }
                    });

                    int x = (int) registerLayout.getWidth();
                    int y = (int) registerLayout.getY();

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
                } else {
                    loginLayout.setVisibility(View.INVISIBLE);
                    btnGoToRegister.setVisibility(View.INVISIBLE);
                    registerLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close();
            }
        });
    }

    void close() {
        if (Build.VERSION.SDK_INT >= 21) {
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
        } else {
            loginLayout.setVisibility(View.VISIBLE);
            registerLayout.setVisibility(View.GONE);
            btnGoToRegister.setVisibility(View.VISIBLE);
        }
    }

    void init() {
        //login page :
        loginLayout = (RelativeLayout) findViewById(R.id.login_layout);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPass = (EditText) findViewById(R.id.et_password);
        etlayoutPhone = (TextInputLayout) findViewById(R.id.et_layout_phone);
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
        etPhoneReg = (EditText) findViewById(R.id.et_phone_r);
        etEmailReg = (EditText) findViewById(R.id.et_email_r);
        etlayoutPhoneReg = (TextInputLayout) findViewById(R.id.et_layout_phone_r);
        etlayoutEmailReg = (TextInputLayout) findViewById(R.id.et_layout_email_r);
        etlayoutUserNameReg = (TextInputLayout) findViewById(R.id.et_layout_username_r);
        etlayoutPassReg = (TextInputLayout) findViewById(R.id.et_layout_password_r);
        etlayoutConfPassReg = (TextInputLayout) findViewById(R.id.et_layout_confirm_password_r);
        btnReg = (Button) findViewById(R.id.btn_register);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void forgotten(String username) {
        Sendf("http://seyyedmahdi.eu-4.evennode.com/passregister", username);
    }

    void log(String username, String password) {
        Sendl("http://seyyedmahdi.eu-4.evennode.com/singinwithpass", username, password);
    }

    void reg(String name, String phn, String password, String mail) {
        Sendr("http://seyyedmahdi.eu-4.evennode.com/singupwithpass", name, phn, password, mail);
    }

    private ProgressDialog pDialog;

    void Sendl(final String URL, final String user, final String pass) {
        Log.d("req", "___send started");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.show();

        final Map<String, String> postParam = new HashMap<String, String>();

        postParam.put("phone", user);
        postParam.put("password", pass);

        ////////////////////////////////////////////////////////

        final Thread send = new Thread(new Runnable() {
            @Override
            public void run() {

                JSONObject obj = new JSONObject(postParam);

                postData(URL, obj, false);

            }
        });

        send.start();
    }

    void Sendf(final String URL, final String user) {
        Log.d("req", "___send started");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("در حال ارسال");
        pDialog.show();

        final Map<String, String> postParam = new HashMap<String, String>();

        postParam.put("id", user);

        ////////////////////////////////////////////////////////

        final Thread send = new Thread(new Runnable() {
            @Override
            public void run() {

                JSONObject obj = new JSONObject(postParam);

                pDforgot(URL, obj);

            }
        });

        send.start();
    }

    void Sendr(final String URL, final String user, final String phn, final String pass, final String mail) {
        Log.d("req", "___send started");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("لطفا صبر کنید");
        pDialog.show();

        final Map<String, String> postParam = new HashMap<String, String>();

        postParam.put("email", mail);
        postParam.put("username", user);
        postParam.put("phone", phn);
        postParam.put("password", pass);

        ////////////////////////////////////////////////////////

        final Thread send = new Thread(new Runnable() {
            @Override
            public void run() {

                JSONObject obj = new JSONObject(postParam);

                postData(URL, obj, true);

            }
        });

        send.start();
    }

    public void postData(String url, JSONObject obj, boolean reg) {
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

            if (reg) {
                if (temp.contains("ok")) {
                    runOnUiThread(new Runnable() {
                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void run() {
                            hidePDialog();
                            close();
                            tt("ثبت نام با موفقیت انجام شد\nلطفا وارد حساب خود شوید");
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hidePDialog();
                            tt("خطا در اطلاعات وارد شده");
                        }
                    });
                }
            } else {
                //login
                if (temp.contains("ok")) {
                    String id = temp.substring(21, temp.length() - 2);
                    tran(id);
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hidePDialog();
                            tt("شما تصدیق نشدید\nدوباره سعی کنید");
                        }
                    });

                }

            }
        } catch (ClientProtocolException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tt("خطا در برقراری ارتباط");
                    hidePDialog();
                }
            });

        } catch (IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tt("خطا در ورودی خروجی");
                    hidePDialog();
                }
            });
        }


    }

    public void pDforgot(String url, JSONObject obj) {
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

            if (temp.contains("ok")) {
                runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        hidePDialog();
                        close();
                        tt("ایمیل خود را چک کنید");
                    }
                });
            } else if (temp.contains("found")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                        tt("چنین کاربری موجود نیست");
                    }
                });
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hidePDialog();
                        tt("خطا در ارسال ایمیل");
                    }
                });
            }


        } catch (ClientProtocolException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tt("خطا در برقراری ارتباط");
                    hidePDialog();
                }
            });

        } catch (IOException e) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tt("خطا در ورودی خروجی");
                    hidePDialog();
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

    void tran(String id) {
        hidePDialog();
        SaveMe(id);
        SaveAccess();
        Intent i = new Intent(LoginActivity.this, HomePageActivity.class);
//        i.putExtra("phn", phn);
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