package com.androidadvance.opensourcebitcoinwallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.androidadvance.opensourcebitcoinwallet.BaseActivity;
import com.androidadvance.opensourcebitcoinwallet.R;
import com.androidadvance.opensourcebitcoinwallet.utils.SecurityHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImportWalletActivity extends BaseActivity {


    @BindView(R.id.btnScanPublicKey)
    Button btnScanPublicKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_import_wallet);
        ButterKnife.bind(this);

        getSupportActionBar().hide();
    }

    @OnClick(R.id.btnScanPublicKey)
    public void onClickBtnScanPublicKey() {

        //TODO REMOVE ME
//        Intent qrCodeIntent = new Intent(ImportWalletActivity.this, QRScannerActivity.class);
//        qrCodeIntent.putExtra("type","ScanPublicKey");
//        startActivity(qrCodeIntent);

        String publicKey = "1PX3eXVsKVZDLBJiF6JZ7Ty2pi5z6ssAof";

        //store it encoded
        SecurityHolder.storePublicKey(ImportWalletActivity.this, publicKey);


        startActivity(new Intent(ImportWalletActivity.this, MainActivity.class));

    }
}
