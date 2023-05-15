package com.tingzq.mvvm.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import com.tingzq.mvvm.utils.Utils;

public class BaseApplication extends Application implements ViewModelStoreOwner{

    private static Application sInstance;

    private ViewModelStore mAppViewModelStore;

    private ViewModelProvider.Factory mFactory = null;


    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
        mAppViewModelStore = new ViewModelStore();
    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     *
     * @param application
     */
    public static synchronized void setApplication(@NonNull Application application) {
        sInstance = application;
        //初始化工具类
        Utils.init(application);
        //注册监听每个activity的生命周期,便于堆栈式管理
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getAppManager().removeActivity(activity);
            }
        });
    }


    public static Application get(){

        if (sInstance == null)
        {
            throw new NullPointerException(
                    "please inherit BaseApplication or call setApplication.");
        }
        return sInstance;

    }

    public ViewModelProvider getAppViewModelProvider(){
        return new ViewModelProvider(this, this.getAppFactory());
    }

    private ViewModelProvider.Factory getAppFactory() {
        if(mFactory == null){
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this);
        }
        return mFactory;
    }


}
