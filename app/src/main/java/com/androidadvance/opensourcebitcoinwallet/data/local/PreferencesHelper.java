package com.androidadvance.opensourcebitcoinwallet.data.local;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {

  private static SharedPreferences mPref;

  public static final String PREF_FILE_NAME = "myapp_shared_prefs";
  private static final String KEY_DEVICE_ID = "device_id";
  private static final String KEY_PUBLIC_KEY = "publicKey";



  public PreferencesHelper(Context context) {
    mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
  }

  public void clear() {
    mPref.edit().clear().apply();
  }


  public String getDeviceID() {
    return mPref.getString(KEY_DEVICE_ID, null);
  }

  public void setDeviceID(String deviceID) {
    mPref.edit().putString(KEY_DEVICE_ID, deviceID).apply();
  }


  //encrypted
  public String getPublicKey() {
    return mPref.getString(KEY_PUBLIC_KEY, null);
  }

  //encrypted
  public void setPublicKey(String publicKey) {
    mPref.edit().putString(KEY_PUBLIC_KEY, publicKey).commit(); //commit instead of apply
  }

}
