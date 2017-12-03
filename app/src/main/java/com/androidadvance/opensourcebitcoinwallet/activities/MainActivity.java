package com.androidadvance.opensourcebitcoinwallet.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.androidadvance.opensourcebitcoinwallet.BaseActivity;
import com.androidadvance.opensourcebitcoinwallet.R;
import com.androidadvance.opensourcebitcoinwallet.fragments.SampleFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import hotchemi.android.rate.AppRate;

public class MainActivity extends BaseActivity {

    @BindView(R.id.relayout_main)
    RelativeLayout relayout_main;

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;


    private MainActivity mContext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().setElevation(0);
        mContext = MainActivity.this;


        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_send) {
                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                    t.replace(R.id.contentContainer, SampleFragment.newInstance("send"));
                    t.commit();
                }
                if (tabId == R.id.tab_home) {
                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                    t.replace(R.id.contentContainer, SampleFragment.newInstance("home"));
                    t.commit();
                }
                if (tabId == R.id.tab_receive) {
                    FragmentTransaction t = getSupportFragmentManager().beginTransaction();
                    t.replace(R.id.contentContainer, SampleFragment.newInstance("receive"));
                    t.commit();
                }
            }
        });

        rate_this_app_logic();

    }


    private void rate_this_app_logic() {
        AppRate.with(this).setInstallDays(10).setLaunchTimes(10).setRemindInterval(2).setShowLaterButton(false).setDebug(false).monitor();
        AppRate.showRateDialogIfMeetsConditions(this);
    }
}


