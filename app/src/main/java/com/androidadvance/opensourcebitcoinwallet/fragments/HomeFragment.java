package com.androidadvance.opensourcebitcoinwallet.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidadvance.opensourcebitcoinwallet.BaseFragment;
import com.androidadvance.opensourcebitcoinwallet.R;

import butterknife.BindView;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.textView_fragmentHome_balance)
    TextView textView_fragmentHome_balance;
    @BindView(R.id.textView_fragmentHome_headline)
    TextView textView_fragmentHome_headline;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textView_fragmentHome_headline.setText("Detecting BTC in your wallet");
        textView_fragmentHome_balance.setText("loading...");

    }
}