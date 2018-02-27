package com.example.asus.richmans;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScrn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_scrn);
        if (!isNetworkAvailable(getBaseContext())) {
            tt("دستگاه به اینترنت متصل نیست");
        }else {
//            try {
//                File f = new File(getFilesDir().getAbsolutePath() + "/.ShData/LoginData.txt");
//                if (!f.exists()) {
//                    Intent i = new Intent(this, Login.class);
//                    startActivity(i);
//                    this.finish();
//                }
//            } catch (Exception e) {
//                //
//            }
        }
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    void tt(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}