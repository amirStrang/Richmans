package com.example.asus.richmans;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ContactUsActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView mail, channel, tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mail = (TextView) findViewById(R.id.txt_email_address);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotomail();
            }
        });
        channel = (TextView) findViewById(R.id.tchannel);
        channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gototelegram("madrasetavangari");
            }
        });
        tid = (TextView) findViewById(R.id.tid);
        tid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gototelegram("hamid8r5ir");
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    void gototelegram(String domain) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tg://resolve?domain=" + domain));
        startActivity(intent);
    }

    void gotomail() {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "اپلیکیشن");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"madresetavangari@gmail.com"});
        emailIntent.setType("text/plain");
        startActivity(emailIntent);
    }
}
