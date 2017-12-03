package com.androidadvance.opensourcebitcoinwallet.activities;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.androidadvance.opensourcebitcoinwallet.BaseActivity;
import com.androidadvance.opensourcebitcoinwallet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import hotchemi.android.rate.AppRate;

public class MainActivity extends BaseActivity {

    @BindView(R.id.relayout_main)
    RelativeLayout relayout_main;

    private MainActivity mContext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().setElevation(0);
        mContext = MainActivity.this;


        rate_this_app_logic();

    }


    //delete me if not needed... [remember to enable the library from build.gradle (app) file
    private void rate_this_app_logic() {
        AppRate.with(this).setInstallDays(10).setLaunchTimes(10).setRemindInterval(2).setShowLaterButton(false).setDebug(false).monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
    }
}


