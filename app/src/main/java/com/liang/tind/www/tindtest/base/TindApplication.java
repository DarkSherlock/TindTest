package com.liang.tind.www.tindtest.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;

import com.facebook.drawee.controller.ControllerListener;
import com.liang.tind.www.tindtest.BuildConfig;
import com.maxwell.imkid.library.app.MaxWellApplication;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import leakcanary.AppWatcher;
import leakcanary.LeakCanary;

public class TindApplication extends Application implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = "TindApplication";
    //    public TindApplication() {
//        super(ShareConstants.TINKER_ENABLE_ALL, MyApplicationLike.class.getCanonicalName(),
//                "com.tencent.tinker.loader.TinkerLoader", false);
//    }
    public static Context sContext = null;
    public static List<Object> list = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        MaxWellApplication.init(this, BuildConfig.DEBUG);
//        DoraemonKit.install(this);
        registerActivityLifecycleCallbacks(this);
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "init StrictMode");
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().setClassInstanceLimit(ControllerListener.class, 2).penaltyLog().build());
        }
        initLeakCanary();
    }

    private void initLeakCanary() {
        Looper.myQueue().addIdleHandler(() -> {
            AppWatcher.Config leakConfig = new AppWatcher.Config(true, true, true, false, false, 5000);
            AppWatcher.setConfig(leakConfig);
            LeakCanary.Config config = LeakCanary.getConfig().newBuilder().dumpHeap(true).build();
            LeakCanary.setConfig(config);
            return false;
        });
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onActivityCreated(): " + activity);
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.i(TAG, "onActivityStarted(): " + activity);
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.i(TAG, "onActivityStopped(): " + activity);
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.i(TAG, "onActivityDestroyed(): " + activity);
    }
}
