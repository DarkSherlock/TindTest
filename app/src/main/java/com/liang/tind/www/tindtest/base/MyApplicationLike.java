package com.liang.tind.www.tindtest.base;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.liang.tind.www.tindtest.util.CrashHandler;
import com.maxwell.imkid.library.app.MaxWellApplication;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.tinker.loader.BuildConfig;
import com.tencent.tinker.loader.app.DefaultApplicationLike;

import androidx.multidex.MultiDex;
import leakcanary.AppWatcher;
import leakcanary.LeakCanary;

public class MyApplicationLike extends DefaultApplicationLike {
  private static final String TAG = "MyApplicationLike";
  public static Application sApplication;

  public final static Handler handeler = new Handler();

  public MyApplicationLike(
      Application application,
      int tinkerFlags,
      boolean tinkerLoadVerifyFlag,
      long applicationStartElapsedTime,
      long applicationStartMillisTime,
      Intent tinkerResultIntent) {
    super(
        application,
        tinkerFlags,
        tinkerLoadVerifyFlag,
        applicationStartElapsedTime,
        applicationStartMillisTime,
        tinkerResultIntent);
  }

  @Override
  public void onCreate() {
    super.onCreate();
    initLeakCanary();

    CrashHandler.getInstance().init(getApplication());
    CrashReport.setIsDevelopmentDevice(getApplication(), BuildConfig.DEBUG);
    // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
    // 调试时，将第三个参数改为true
    Bugly.init(getApplication(), "1406e2ba7a", true);
    MaxWellApplication.init(getApplication(), BuildConfig.DEBUG);
    //        initImageLoader();

    logBuild();
    sApplication = getApplication();
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

  //    private void initImageLoader() {
  //        File cacheDir = StorageUtils.getCacheDirectory(getApplication()); //缓存文件夹路径
  //        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplication())
  //                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
  // 内存缓存文件的最大长宽
  //                .diskCacheExtraOptions(480, 800, null) // 本地缓存的详细信息(缓存的最大长宽)，最好不要设置这个
  //                .threadPoolSize(3) // default 线程池内加载的数量
  //                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
  //                .denyCacheImageMultipleSizesInMemory()
  //                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)) //可以通过自己的内存缓存实现
  //                .memoryCacheSize(2 * 1024 * 1024) // 内存缓存的最大值
  //                .memoryCacheSizePercentage(13) // default
  //                .diskCache(new LimitedAgeDiskCache(cacheDir,8*1024*1024)) // default 可以自定义缓存路径
  //                .diskCacheSize(50 * 1024 * 1024) // 50 Mb sd卡(本地)缓存的最大值
  //                .diskCacheFileCount(100) // 可以缓存的文件数量
  //// default为使用HASHCODE对UIL进行加密命名， 还可以用MD5(new Md5FileNameGenerator())加密
  //                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
  //                .imageDownloader(new BaseImageDownloader(getApplication())) // default
  //                .imageDecoder(new BaseImageDecoder(true)) // default
  //                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
  //                .writeDebugLogs() // 打印debug log
  //                .build(); //开始构建
  //
  //        ImageLoader.getInstance().init(config);
  //    }

  @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
  @Override
  public void onBaseContextAttached(Context base) {
    super.onBaseContextAttached(base);
    // you must install multiDex whatever tinker is installed!
    MultiDex.install(base);

    // 安装tinker
    // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
    Beta.installTinker(this);
  }

  @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
  public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
    getApplication().registerActivityLifecycleCallbacks(callbacks);
  }

  protected void logBuild() {
    Log.i(TAG, "Device : " + Build.DEVICE);
    Log.i(TAG, "Device System Code: " + Build.VERSION.CODENAME);
    Log.i(TAG, "Device System RELEASE: " + Build.VERSION.RELEASE);
    Log.i(TAG, "Device System SDK_INT: " + Build.VERSION.SDK_INT);
  }
}
