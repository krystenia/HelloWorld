package com.example.sf.myapplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 89003337 on 2017/4/6.
 */
@Singleton
@Component(modules = {AppModule.class,GithubApiModule.class})
interface AppComponent {
    void inject(ReposListActivity activity);
}
