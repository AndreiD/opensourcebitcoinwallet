package com.androidadvance.opensourcebitcoinwallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import com.androidadvance.opensourcebitcoinwallet.BaseActivity;
import com.androidadvance.opensourcebitcoinwallet.R;
import com.androidadvance.opensourcebitcoinwallet.fragments.HomeFragment;
import com.androidadvance.opensourcebitcoinwallet.fragments.ReceiveFragment;
import com.androidadvance.opensourcebitcoinwallet.fragments.SampleFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import hotchemi.android.rate.AppRate;

public class MainActivity extends BaseActivity {

  @BindView(R.id.relayout_main) RelativeLayout relayout_main;

  @BindView(R.id.theToolbar) Toolbar theToolbar;

  @BindView(R.id.bottomBar) BottomBar bottomBar;

  private MainActivity mContext;

  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setSupportActionBar(theToolbar);
    getSupportActionBar().setElevation(0);
    mContext = MainActivity.this;

    bottomBar.setDefaultTab(R.id.tab_home);
    bottomBar.setOnTabSelectListener(tabId -> {
      if (tabId == R.id.tab_send) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.contentContainer, SampleFragment.newInstance("send"));
        t.addToBackStack("fragment_send");
        t.commit();
      }
      if (tabId == R.id.tab_home) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.contentContainer, HomeFragment.newInstance());
        t.addToBackStack("fragment_home");
        t.commit();
      }
      if (tabId == R.id.tab_receive) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.contentContainer, ReceiveFragment.newInstance());
        t.addToBackStack("fragment_receive");
        t.commit();
      }
      if (tabId == R.id.tab_settings) {
        startActivity(new Intent(MainActivity.this, SettingsActivity.class));
      }
    });

    rate_this_app_logic();
  }

  private void rate_this_app_logic() {
    AppRate.with(this).setInstallDays(10).setLaunchTimes(10).setRemindInterval(2).setShowLaterButton(false).setDebug(false).monitor();
    AppRate.showRateDialogIfMeetsConditions(this);
  }
}


