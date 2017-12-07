package com.androidadvance.opensourcebitcoinwallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.androidadvance.opensourcebitcoinwallet.BaseActivity;
import com.androidadvance.opensourcebitcoinwallet.R;
import com.androidadvance.opensourcebitcoinwallet.qrscanner.QRScannerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImportWalletActivity extends BaseActivity {


    @BindView(R.id.btnScanBTCAddresss)
    Button btnScanBTCAddresss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_wallet);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btnScanBTCAddresss)
    public void onClickScanbtnScanBTCAddresss() {

        Intent qrCodeIntent = new Intent(ImportWalletActivity.this, QRScannerActivity.class);
        qrCodeIntent.putExtra("type","ScanBtcAddress");
        startActivity(qrCodeIntent);

    }
}
