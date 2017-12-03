package com.androidadvance.opensourcebitcoinwallet;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.socks.library.KLog;


public class BaseApplication extends Application {

    private boolean isDebuggable;

    @Override
    public void onCreate() {
        super.onCreate();

        isDebuggable = (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));

        if (isDebuggable) {
            KLog.init(true);
        } else {
            KLog.init(false);
        }


    }

    public boolean isDebuggable() {
        return isDebuggable;
    }

    public static BaseApplication get(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }
}
