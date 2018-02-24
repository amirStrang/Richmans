package com.example.asus.richmans.NotificationManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Mr.Anonymous on 2/23/2018.
 */

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, MyNewIntentService.class);
        context.startService(i);
    }
}
