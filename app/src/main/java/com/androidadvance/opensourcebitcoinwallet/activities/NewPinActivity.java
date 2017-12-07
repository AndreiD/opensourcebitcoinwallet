package com.androidadvance.opensourcebitcoinwallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.androidadvance.opensourcebitcoinwallet.BaseActivity;
import com.androidadvance.opensourcebitcoinwallet.R;
import com.androidadvance.opensourcebitcoinwallet.utils.DialogFactory;
import com.androidadvance.opensourcebitcoinwallet.utils.SecurityHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NewPinActivity extends BaseActivity {

    @BindView(R.id.editText_pin1)
    EditText editText_pin1;

    @BindView(R.id.editText_pin2)
    EditText editText_pin2;

    @BindView(R.id.btn_save_new_pin)
    Button btn_save_new_pin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_pin);
        ButterKnife.bind(this);

        getSupportActionBar().hide();

        //TODO: delete me
        editText_pin1.setText("123123");
        editText_pin2.setText("123123");


    }


    @OnClick(R.id.btn_save_new_pin)
    public void onClickSaveNewPin() {
        String pin1 = editText_pin1.getText().toString();
        String pin2 = editText_pin2.getText().toString();
        if (!pin1.equals(pin2)) {
            DialogFactory.error_toast(NewPinActivity.this, "Pin 1 is not equal to pin 2.").show();
            return;
        }

        SecurityHolder.pin = pin1;

        startActivity(new Intent(NewPinActivity.this, ImportWalletActivity.class));
        finish();

    }
}
