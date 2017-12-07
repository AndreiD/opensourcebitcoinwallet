package com.androidadvance.opensourcebitcoinwallet.data.remote;

import android.content.Context;

import com.androidadvance.opensourcebitcoinwallet.BuildConfig;
import com.google.gson.JsonObject;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BlockchainInfoAPI {

  String BASE_URL = "https://blockchain.info/";

  @GET("multiaddr") Call<JsonObject> getBalance(@Query("active") String from);

  //for the conversion to USD
  @GET("ticker") Call<JsonObject> getTicker();

  class Factory {
    private static BlockchainInfoAPI service;

    public static BlockchainInfoAPI getIstance(Context context) {
      if (service == null) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(25, TimeUnit.SECONDS);
        builder.connectTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);

        //builder.certificatePinner(new CertificatePinner.Builder().add("*.androidadvance.com", "sha256/RqzElicVPA6LkKm9HblOvNOUqWmD+4zNXcRb+WjcaAE=")
        //    .add("*.xxxxxx.com", "sha256/8Rw90Ej3Ttt8RRkrg+WYDS9n7IS03bk5bjP/UXPtaY8=")
        //    .add("*.xxxxxxx.com", "sha256/Ko8tivDrEjiY90yGasP6ZpBU4jwXvHqVvQI0GS3GNdA=")
        //    .add("*.xxxxxxx.com", "sha256/VjLZe/p3W/PJnd6lL8JVNBCGQBZynFLdZSTIqcO0SJ8=")
        //    .build());

        if (BuildConfig.DEBUG) {
          HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
          interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
          builder.addInterceptor(interceptor);
        }


        Retrofit retrofit =
            new Retrofit.Builder().client(builder.build()).addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        service = retrofit.create(BlockchainInfoAPI.class);
        return service;
      } else {
        return service;
      }
    }
  }
}
