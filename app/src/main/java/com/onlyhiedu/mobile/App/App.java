package com.onlyhiedu.mobile.App;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.onlyhiedu.mobile.Dagger.Component.AppComponent;
import com.onlyhiedu.mobile.Dagger.Component.DaggerAppComponent;
import com.onlyhiedu.mobile.Dagger.Modul.AppModule;
import com.onlyhiedu.mobile.Utils.DaoUtil;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import java.io.InputStream;

import io.agore.openvcall.model.CurrentUserSettings;
import io.agore.openvcall.model.WorkerThread;
import okhttp3.OkHttpClient;

/**
 * 全局应用程序类
 * Created by xuwc on 2016/11/21.
 */
public class App extends Application {

    private static App instance;

    public static synchronized App getInstance() {
        return instance;
    }

    static {
        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }


    private WorkerThread mWorkerThread;

    public synchronized void initWorkerThread() {
        if (mWorkerThread == null) {
            mWorkerThread = new WorkerThread(getApplicationContext());
            mWorkerThread.start();

            mWorkerThread.waitForReady();
        }
    }

    public synchronized WorkerThread getWorkerThread() {
        return mWorkerThread;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //初始化日志
        Logger.init(getPackageName()).hideThreadInfo();

        LeakCanary.install(this);

        initGlide();

        DaoUtil.getInstance(this);
    }
    private void initGlide(){
        Glide.get(this).register(GlideUrl.class,InputStream.class,new OkHttpUrlLoader.Factory(new OkHttpClient()));
    }


    public static AppComponent getAppComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(instance))
                .build();
    }
    public static final CurrentUserSettings mVideoSettings = new CurrentUserSettings();

}
