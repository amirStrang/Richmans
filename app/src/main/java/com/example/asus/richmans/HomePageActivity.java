package com.example.asus.richmans;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.richmans.NotificationManager.MyReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RelativeLayout btnLearn, btnStore, btnHowToStart, btnHistory, btnAboutUs, btnAboutGame, btnContactUs, btnShare, btnReset;
    TextView txtCredit, txtDay;
    ProgressBar prbCredit;

    String phn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.activity_home_page);

        init();

        ntf();

        showDialog();

        phn = readFileAsString(getBaseContext(), getFilesDir().getAbsolutePath() + "/.richmans/phn.txt");

        //loadCredit("http://ahmadiTest.sepantahost.com/api/GetData?phn=" + phn);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCredit("http://ahmadiTest.sepantahost.com/api/GetData?phn=" + phn);
        Toast.makeText(this, "Fffff", Toast.LENGTH_LONG).show();
    }

    void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnAboutGame = (RelativeLayout) findViewById(R.id.btn_about_game);
        btnAboutUs = (RelativeLayout) findViewById(R.id.btn_about_us);
        btnContactUs = (RelativeLayout) findViewById(R.id.btn_contact_us);
        btnHistory = (RelativeLayout) findViewById(R.id.btn_history);
        btnLearn = (RelativeLayout) findViewById(R.id.btn_learn);
        btnHowToStart = (RelativeLayout) findViewById(R.id.btn_store);
        btnReset = (RelativeLayout) findViewById(R.id.btn_reset);
        btnStore = (RelativeLayout) findViewById(R.id.btn_how_to_start);
        btnShare = (RelativeLayout) findViewById(R.id.btn_share);

        txtCredit = (TextView) findViewById(R.id.txtCredit);
        txtDay = (TextView) findViewById(R.id.txt_day);

        prbCredit = (ProgressBar) findViewById(R.id.prb_credit);

        btnAboutGame.setOnClickListener(this);
        btnAboutUs.setOnClickListener(this);
        btnContactUs.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnLearn.setOnClickListener(this);
        btnHowToStart.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnStore.setOnClickListener(this);
        btnShare.setOnClickListener(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i;
        switch (id) {
            case R.id.item_buy_account:
                i = new Intent(this, Account.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent i;

        switch (id) {
            case R.id.btn_about_us:
                i = new Intent(HomePageActivity.this, AboutUsActivity.class);
                startActivity(i);
                break;
            case R.id.btn_about_game:
                i = new Intent(HomePageActivity.this, AboutGameActivity.class);
                startActivity(i);
                break;
            case R.id.btn_contact_us:
                i = new Intent(HomePageActivity.this, ContactUsActivity.class);
                startActivity(i);
                break;
            case R.id.btn_history:
                i = new Intent(HomePageActivity.this, HistoryActivity.class);
                startActivity(i);
                break;
            case R.id.btn_learn:
                i = new Intent(HomePageActivity.this, LearnPageActivity.class);
                startActivity(i);
                break;
            case R.id.btn_reset:
                i = new Intent(HomePageActivity.this, SetBaseMoneyActivity.class);
                i.putExtra("phn", phn);
                startActivity(i);
                HomePageActivity.this.finish();
                break;
            case R.id.btn_how_to_start:
                i = new Intent(HomePageActivity.this, StoreActivity.class);
                startActivity(i);
                break;
            case R.id.btn_store:
                i = new Intent(HomePageActivity.this, SetBaseMoneyActivity.class);
                i.putExtra("phn", phn);
                startActivity(i);
                HomePageActivity.this.finish();
                break;
            case R.id.btn_share:
                share();
                break;
        }
    }

//    void inc(final int from, final int to) {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                txtCredit.setText(from + "");
//                if (from < to)
//                    inc(from + 100, to);
//            }
//        }, 1);
//    }

    private void loadCredit(String url) {
        prbCredit.setVisibility(View.VISIBLE);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    response = response.substring(1, response.length() - 1);
                    JSONObject jsonObject = new JSONObject(response);

                    int credit = jsonObject.getInt("credit");
                    int day = jsonObject.getInt("Day");

                    txtDay.setText((day >= 0) ? day + "" : "0");

                    prbCredit.setVisibility(View.INVISIBLE);
                    txtCredit.setVisibility(View.VISIBLE);

                    txtCredit.setText((credit >= 0) ? credit + "" : "0");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();

            }

        });

        int socketTimeout = 30000;

        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);

        requestQueue.add(stringRequest);

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


    private PendingIntent pendingIntent;

    void ntf() {

        Intent notifyIntent = new Intent(this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                1, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.AM_PM, Calendar.PM);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                calendar.getTimeInMillis(), 24 * 60 * 60 * 1000, pendingIntent);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }

    void share() {
        String message = "ما را دنبال کنید";

        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, message);
        i.setType("message/rfc822");
        startActivity(Intent.createChooser(i, "Select your app :"));
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

}
