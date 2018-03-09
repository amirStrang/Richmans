package com.example.asus.richmans;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.asus.richmans.util.IabHelper;
import com.example.asus.richmans.util.IabResult;
import com.example.asus.richmans.util.Inventory;
import com.example.asus.richmans.util.Purchase;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Account extends AppCompatActivity {

    // Debug tag, for logging
    static final String TAG = "RICHMANS_ACCOUNT";

    // SKUs for our products: the premium upgrade (non-consumable)
    static final String SKU_SILVER = "SILVER";
    static final String SKU_GOLD = "GOLD";

    // (arbitrary) request code for the purchase flow
    static final int RC_REQUEST = 10001;

    // The helper object
    IabHelper mHelper;

    ImageView silver;
    ImageView gold;

    Button s;
    Button g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        gold = (ImageView) findViewById(R.id.check_gold);
        silver = (ImageView) findViewById(R.id.check_silver);

        String base64EncodedPublicKey = "MIHNMA0GCSqGSIb3DQEBAQUAA4G7ADCBtwKBrwDcqGPwNQIvhDlbGEHrSCQY58EMf0A6v5HGBagUvEXpzKYZwKARcOXxA7yhiIPFq9lwsrYIzad10gXdycFEZTctSIg0PlFuJxGA61YGxj63KdUWtfbOqrZ3U66zMn8kh85se9SIXs2hcKYuDDnG1Hhsm0r1PxxBAGR/01yxxLddC6QJQx0EgkjH983PCgKueVhh4axVLadqE5TF5O1JqmyqAjrJdPKAOdQlyEsfBncCAwEAAQ==";

        mHelper = new IabHelper(this, base64EncodedPublicKey);

        Log.d(TAG, "Starting setup.");

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                Log.d(TAG, "Setup finished.");

                if (!result.isSuccess()) {
                    // Oh noes, there was a problem.
                    Log.d(TAG, "Problem setting up In-app Billing: " + result);
                }
                // Hooray, IAB is fully set up!
                mHelper.queryInventoryAsync(mGotInventoryListener);
                String str = readFileAsString(getFilesDir().getAbsolutePath() + "/.richmans/acc.txt");
                loadAccount(str);
            }
        });
        s = (Button) findViewById(R.id.btn_buy_silver);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHelper.launchPurchaseFlow(Account.this, SKU_SILVER, RC_REQUEST, mPurchaseFinishedListener, "payload-string");
            }
        });
        g = (Button) findViewById(R.id.btn_buy_gold);
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHelper.launchPurchaseFlow(Account.this, SKU_GOLD, RC_REQUEST, mPurchaseFinishedListener, "payload-string");
            }
        });


    }

    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            Log.d(TAG, "Query inventory finished.");
            if (result.isFailure()) {
                Log.d(TAG, "Failed to query inventory: " + result);
                return;
            } else {
                Log.d(TAG, "Query inventory was successful.");
            }

            Log.d(TAG, "Initial inventory query finished; enabling main UI.");
        }
    };

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isFailure()) {
                Log.d(TAG, "Error purchasing: " + result);

                ////////////////////////////////
/*
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Server
                        send("BRONZE");
                        //File
                        SaveAccess("BRONZE");
                        // UI
                        loadAccount("BRONZE");
                    }
                });

*/
                ///////////////////////////////
                return;
            } else if (purchase.getSku().equals(SKU_SILVER)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Server
                        send("SILVER");
                        //File
                        SaveAccess("SILVER");
                        // UI
                        loadAccount("SILVER");
                    }
                });
                return;
            } else if (purchase.getSku().equals(SKU_GOLD)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Server
                        send("GOLD");
                        //File
                        SaveAccess("GOLD");
                        // UI
                        loadAccount("GOLD");
                    }
                });
                return;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult(" + requestCode + "," + resultCode + "," + data);

        // Pass on the activity result to the helper for handling
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        } else {
            Log.d(TAG, "onActivityResult handled by IABUtil.");
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }

    void loadAccount(String str) {
        if (str.equals("BRONZE")) {
            s.setVisibility(View.VISIBLE);
            g.setVisibility(View.VISIBLE);
        } else if (str.equals("SILVER")) {
            g.setVisibility(View.VISIBLE);
            silver.setVisibility(View.VISIBLE);
            s.setVisibility(View.INVISIBLE);
        } else if (str.equals("GOLD")) {
            silver.setVisibility(View.VISIBLE);
            gold.setVisibility(View.VISIBLE);
            s.setVisibility(View.INVISIBLE);
            g.setVisibility(View.INVISIBLE);
        }

    }

    public String readFileAsString(String filePath) {
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

    void SaveAccess(String acc) {

        File root = getFilesDir();
        File dir = new File(root.getAbsolutePath() + "/.richmans");
        dir.mkdirs();
        File file = new File(dir, "acc.txt");

        try {
            FileOutputStream f = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(f);
            pw.println(acc);
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

    void send(String str) {
        String phn = readFileAsString(getFilesDir().getAbsolutePath() + "/.richmans/phn.txt");
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://www.yoursite.com/myexample.php");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("Number", phn));
            nameValuePairs.add(new BasicNameValuePair("Access", str));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

}
