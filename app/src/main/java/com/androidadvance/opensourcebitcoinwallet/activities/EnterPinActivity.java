package com.androidadvance.opensourcebitcoinwallet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;

import com.androidadvance.opensourcebitcoinwallet.BaseActivity;
import com.androidadvance.opensourcebitcoinwallet.R;
import com.androidadvance.opensourcebitcoinwallet.data.remote.BlockchainInfoAPI;
import com.androidadvance.opensourcebitcoinwallet.utils.BalanceHolder;
import com.androidadvance.opensourcebitcoinwallet.utils.DialogFactory;
import com.androidadvance.opensourcebitcoinwallet.utils.SecurityHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.gson.JsonObject;
import com.socks.library.KLog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterPinActivity extends BaseActivity {

  @BindView(R.id.editText_pin1) EditText editText_pin1;

  @BindView(R.id.btn_verify_pin) Button btn_verify_pin;

  private BlockchainInfoAPI blockchainInfoAPI;
  private EnterPinActivity mContext;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_enter_pin);
    ButterKnife.bind(this);
    mContext = EnterPinActivity.this;

    getSupportActionBar().hide();

    //TODO: delete me
    editText_pin1.setText("123123");
  }

  @OnClick(R.id.btn_verify_pin) public void onClickSaveVerify() {
    String pin1 = editText_pin1.getText().toString();

    //try to decrypt with the entered pin
    SecurityHolder.pin = pin1;
    if (SecurityHolder.getPublicKey(EnterPinActivity.this) == null) {
      new CountDownTimer(1000, 1000) {
        @Override public void onTick(long l) {
        }

        @Override public void onFinish() {
          DialogFactory.error_toast(EnterPinActivity.this, "Incorrect PIN. Please try again").show();
        }
      }.start();
      return;
    }

    getTheBalances();

    //pin is correct
    startActivity(new Intent(EnterPinActivity.this, MainActivity.class));
    finish();
  }

  private void getTheBalances() {

    blockchainInfoAPI = BlockchainInfoAPI.Factory.getIstance(mContext);
    blockchainInfoAPI.getBalance("19TtHo1CYo8WTF3mTQdAfvkH3q8s2es46r").enqueue(new Callback<JsonObject>() {
      @Override public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        JsonObject responseJsonObject = response.body();
        if (responseJsonObject == null) {
          DialogFactory.error_toast(mContext, "Could not get the balance for your address").show();
          return;
        }
        double finalBalance = formatBalance(responseJsonObject.getAsJsonObject("wallet").get("final_balance").getAsDouble());
        KLog.d("Final balance is " + finalBalance);

        BalanceHolder.balanceBTC = finalBalance;
        convertBalanceToUSD(finalBalance);
      }

      @Override public void onFailure(Call<JsonObject> call, Throwable t) {
        DialogFactory.error_toast(mContext, "Could not get the balance for your address").show();
      }
    });
  }

  private void convertBalanceToUSD(double finalBalance) {
    blockchainInfoAPI.getTicker().enqueue(new Callback<JsonObject>() {
      @Override public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        JsonObject responseJsonObject = response.body();
        double oneBTCinUSD = responseJsonObject.getAsJsonObject("USD").get("15m").getAsDouble();
        BalanceHolder.btcConvertedToUSD = round(oneBTCinUSD * finalBalance, 2);
      }

      @Override public void onFailure(Call<JsonObject> call, Throwable t) {
        KLog.e("failed to convert it to USD");
      }
    });
  }

  public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
  }

  private static double formatBalance(double balance) {

    balance = balance / 100000000;
    String number = String.valueOf(balance);

    int point = number.indexOf(".");
    if (point != -1) {
      String after = number.substring(point + 1);
      int afterint = after.length();
      afterint = 8 - afterint;
      for (int i = 0; i < afterint; i++) {
        number += "0";
      }
    } else {
      number += ".00000000";
    }
    return Double.valueOf(number);
  }
}
