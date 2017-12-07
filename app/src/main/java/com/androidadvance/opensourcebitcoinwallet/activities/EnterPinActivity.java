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
import com.androidadvance.opensourcebitcoinwallet.utils.DUtils;
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

    editText_pin1.setText("123123"); //TODO: DELETE ME
    btn_verify_pin.performClick(); //TODO: DELETE ME
  }

  @OnClick(R.id.btn_verify_pin) public void onClickSaveVerify() {
    String pin1 = editText_pin1.getText().toString();

    //try to decrypt with the entered pin
    SecurityHolder.pin = pin1;
    if (SecurityHolder.getBTCAddress(EnterPinActivity.this) == null) {
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
  }

  private void getTheBalances() {

    blockchainInfoAPI = BlockchainInfoAPI.Factory.getIstance(mContext);
    blockchainInfoAPI.getBalance(SecurityHolder.btcAddress).enqueue(new Callback<JsonObject>() {
      @Override public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        JsonObject responseJsonObject = response.body();
        if (responseJsonObject == null) {
          DialogFactory.error_toast(mContext, "Could not get the balance for your address").show();
          return;
        }
        String finalBalance = DUtils.convertSatoshiToBTC(responseJsonObject.getAsJsonObject("wallet").get("final_balance").getAsDouble());
        KLog.d("Final balance is " + finalBalance);

        BalanceHolder.balanceBTC = Double.valueOf(finalBalance);

        BalanceHolder.txs = responseJsonObject.getAsJsonArray("txs");
        convertBalanceToUSD(Double.valueOf(finalBalance));
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
        BalanceHolder.btcConvertedToUSD = DUtils.round(oneBTCinUSD * finalBalance, 2);

        startMainActivity();
      }

      @Override public void onFailure(Call<JsonObject> call, Throwable t) {
        KLog.e("failed to convert it to USD");
      }
    });
  }

  //balanced are downloaded ok
  private void startMainActivity() {
    startActivity(new Intent(EnterPinActivity.this, MainActivity.class));
    finish();
  }


}
