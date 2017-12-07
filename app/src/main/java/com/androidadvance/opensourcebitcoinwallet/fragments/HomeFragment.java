package com.androidadvance.opensourcebitcoinwallet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.androidadvance.opensourcebitcoinwallet.BaseFragment;
import com.androidadvance.opensourcebitcoinwallet.R;
import com.androidadvance.opensourcebitcoinwallet.utils.BalanceHolder;
import com.socks.library.KLog;

public class HomeFragment extends BaseFragment {

  @BindView(R.id.textView_fragmentHome_balance) TextView textView_fragmentHome_balance;
  @BindView(R.id.textView_fragmentHome_headline) TextView textView_fragmentHome_headline;

  public HomeFragment() {
  }

  public static HomeFragment newInstance() {
    HomeFragment fragment = new HomeFragment();
    KLog.d("NEW FRAGMETN INSTANCE CREATED!");
    fragment.setRetainInstance(true);
    return fragment;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_home, container, false);
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    textView_fragmentHome_headline.setText("Current Balance");
    textView_fragmentHome_balance.setText(String.valueOf(BalanceHolder.balanceBTC) + " BTC (" + BalanceHolder.btcConvertedToUSD + " USD)");
  }
}