package com.androidadvance.opensourcebitcoinwallet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.androidadvance.opensourcebitcoinwallet.BaseFragment;
import com.androidadvance.opensourcebitcoinwallet.R;
import com.androidadvance.opensourcebitcoinwallet.utils.BalanceHolder;
import com.androidadvance.opensourcebitcoinwallet.utils.DUtils;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.socks.library.KLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends BaseFragment {

  @BindView(R.id.textView_fragmentHome_balance) TextView textView_fragmentHome_balance;
  @BindView(R.id.textView_fragmentHome_headline) TextView textView_fragmentHome_headline;
  @BindView(R.id.recyclerView_home) RecyclerView recyclerView_home;
  private HomeAdapter mAdapter;

  public HomeFragment() {
  }

  public static HomeFragment newInstance() {
    HomeFragment fragment = new HomeFragment();
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



    recyclerView_home.setHasFixedSize(true);
        recyclerView_home.setLayoutManager(new LinearLayoutManager(getActivity()));
    ArrayList<String> input = new ArrayList<>();

    JsonArray arrayTxs = BalanceHolder.txs;
    for (int i = 0; i < arrayTxs.size(); i++) {
      JsonObject jTx = arrayTxs.get(i).getAsJsonObject();

      //setup the time of the transaction
      SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss z", Locale.getDefault());
      Date time=new java.util.Date(jTx.get("time").getAsLong()*1000);
      input.add(sdf.format(time)  + "###" + DUtils.convertSatoshiToBTC(jTx.get("result").getAsLong()) + "###" + DUtils.convertSatoshiToBTC(jTx.get("balance").getAsLong()));
    }
    mAdapter = new HomeAdapter(input);
    recyclerView_home.setAdapter(mAdapter);

  }
}