package com.example.sf.myapplication;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import utils.RequestManager;

/**
 * Created by 89003337 on 2017/3/23.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        RequestManager.init(this);
    }
}
