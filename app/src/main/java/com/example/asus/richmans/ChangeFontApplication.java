package com.example.asus.richmans;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ChangeFontApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/BKoodakBold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
