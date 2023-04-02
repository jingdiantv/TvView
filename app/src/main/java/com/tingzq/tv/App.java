package com.tingzq.tv;

import android.content.Context;
import android.support.annotation.Nullable;

import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDex;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.bugly.crashreport.CrashReport;
import com.tingzq.mvvm.base.BaseApplication;

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        CrashReport.initCrashReport(getApplicationContext(), "4c4e985bd4", true);

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder().tag("TvView").build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, @Nullable @androidx.annotation.Nullable String tag) {
                return BuildConfig.DEBUG;
            }
        });

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(getApplicationContext());
    }
}
