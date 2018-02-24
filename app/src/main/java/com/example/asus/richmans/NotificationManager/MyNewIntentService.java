package com.example.asus.richmans.NotificationManager;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

import com.example.asus.richmans.HomePageActivity;
import com.example.asus.richmans.R;

/**
 * Created by Mr.Anonymous on 2/23/2018.
 */

public class MyNewIntentService extends IntentService {

    public MyNewIntentService() {
        super("MyNewIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        time.notify(getApplicationContext());
    }
}
