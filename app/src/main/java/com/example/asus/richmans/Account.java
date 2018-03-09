package com.example.asus.richmans;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.asus.richmans.util.IabHelper;
import com.example.asus.richmans.util.IabResult;
import com.example.asus.richmans.util.Inventory;
import com.example.asus.richmans.util.Purchase;

public class Account extends AppCompatActivity {

    // Debug tag, for logging
    static final String TAG = "RICHMANS_ACCOUNT";

    // SKUs for our products: the premium upgrade (non-consumable)
    static final String SKU_SILVER = "777";
    static final String SKU_GOLD = "23423463623";


    // Does the user have the premium upgrade?
    boolean mIsPremium = false;

    // (arbitrary) request code for the purchase flow
    static final int RC_REQUEST = 10001;

    // The helper object
    IabHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

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
            }
        });
        Button s = (Button) findViewById(R.id.btn_buy_silver);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHelper.launchPurchaseFlow(Account.this, SKU_SILVER, RC_REQUEST, mPurchaseFinishedListener, "payload-string");
                int a = 2;

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
                // does the user have the premium upgrade?
                mIsPremium = inventory.hasPurchase(SKU_SILVER);

                // update UI accordingly

                Log.d(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));
            }

            Log.d(TAG, "Initial inventory query finished; enabling main UI.");
        }
    };

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            if (result.isFailure()) {
                Log.d(TAG, "Error purchasing: " + result);
                return;
            } else if (purchase.getSku().equals(SKU_SILVER)) {
                // give user access to premium content and update the UI
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

}
