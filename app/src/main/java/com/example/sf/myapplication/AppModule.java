package com.example.sf.myapplication;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 89003337 on 2017/4/6.
 */
@Module
public class AppModule {
    @Provides
    public Application provideApplication() {
        return application;
    }

    public  AppModule(Application application) {
        this.application = application;
    }

    private Application application;

}
