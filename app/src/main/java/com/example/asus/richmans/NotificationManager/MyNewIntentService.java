package com.example.asus.richmans.NotificationManager;

import android.app.IntentService;
import android.content.Intent;

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
