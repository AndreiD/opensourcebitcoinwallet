package com.androidadvance.opensourcebitcoinwallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;

import com.androidadvance.opensourcebitcoinwallet.BaseActivity;
import com.androidadvance.opensourcebitcoinwallet.R;
import com.androidadvance.opensourcebitcoinwallet.data.local.PreferencesHelper;
import com.androidadvance.opensourcebitcoinwallet.utils.CommonUtils;
import com.androidadvance.opensourcebitcoinwallet.utils.DialogFactory;


public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        //check if device is rooted
        if (CommonUtils.isRooted()) {
            DialogFactory.error_toast(SplashActivity.this, "This application does not run on rooted devices. Sorry :(").show();
            finish();
        }


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

        PreferencesHelper preferencesHelper = new PreferencesHelper(SplashActivity.this);

        if (preferencesHelper.getPublicKey() == null) {
            //--- show create pin activity ---
            startActivity(new Intent(this, PinActivity.class));
        }

    }
}
