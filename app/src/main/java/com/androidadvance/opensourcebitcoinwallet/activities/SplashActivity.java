package com.androidadvance.opensourcebitcoinwallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import com.androidadvance.opensourcebitcoinwallet.BaseActivity;
import com.androidadvance.opensourcebitcoinwallet.R;


public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();


        new CountDownTimer(500, 500) {

            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                check_wallet_present();
            }
        }.start();
    }

    private void check_wallet_present() {

        boolean isWalletPresent = false;

        if (!isWalletPresent) {
            //--- show create pin activity ---
            startActivity(new Intent(this, PinActivity.class));
        }

    }
}
