package com.example.sf.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by 89003337 on 2017/4/6.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setupActivityComponent(MyApplication.getsInstance().getAppComponent());
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);
    protected abstract int getLayoutId();
    protected abstract void init();
}