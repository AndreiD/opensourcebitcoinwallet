package com.androidadvance.opensourcebitcoinwallet.utils;

import android.content.Context;

import com.androidadvance.opensourcebitcoinwallet.data.local.PreferencesHelper;
import com.tozny.crypto.android.AesCbcWithIntegrity;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class SecurityHolder {
  public static String pin = "";
  public static String btcAddress = "";

  public static void storeBTCAddress(Context contxt, String btcAddress) {
    try {
      PreferencesHelper preferencesHelper = new PreferencesHelper(contxt);
      AesCbcWithIntegrity.SecretKeys key = AesCbcWithIntegrity.generateKeyFromPassword(SecurityHolder.pin, preferencesHelper.getDeviceID());
      AesCbcWithIntegrity.CipherTextIvMac cipherTextIvMac = AesCbcWithIntegrity.encrypt(btcAddress, key, "UTF-8");
      preferencesHelper.setBTCAddress(cipherTextIvMac.toString());
    } catch (GeneralSecurityException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
  }

  public static String getBTCAddress(Context contxt) {
    try {
      PreferencesHelper preferencesHelper = new PreferencesHelper(contxt);
      AesCbcWithIntegrity.CipherTextIvMac cipherTextIvMac = new AesCbcWithIntegrity.CipherTextIvMac(preferencesHelper.getBTCAddress());
      AesCbcWithIntegrity.SecretKeys key = AesCbcWithIntegrity.generateKeyFromPassword(SecurityHolder.pin, preferencesHelper.getDeviceID());
      String plainText = AesCbcWithIntegrity.decryptString(cipherTextIvMac, key);
      btcAddress = plainText;
      return plainText;
    } catch (GeneralSecurityException | UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
