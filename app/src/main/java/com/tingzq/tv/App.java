package com.tingzq.tv;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.tencent.bugly.crashreport.CrashReport;
import com.tingzq.mvvm.base.BaseApplication;
import com.tingzq.mvvm.utils.QLog;

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        CrashReport.initCrashReport(getApplicationContext(), "4c4e985bd4", true);
        QLog.init(BuildConfig.DEBUG);

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(getApplicationContext());
    }
}
