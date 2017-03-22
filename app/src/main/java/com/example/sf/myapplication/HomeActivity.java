package com.example.sf.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 89003337 on 2017/3/22.
 */

public class HomeActivity extends AppCompatActivity {

    private String tag="HomeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        test();
    }

    private void test(){
        Observable<String> observable=Observable.create(new ObservableOnSubscribe<String>(){

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hello");
                e.onNext("RXJAVA");
                e.onComplete();
            }
        });

        Observer<String> observer=new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(tag,"onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(tag,"onNext:"+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(tag,"onError");
            }

            @Override
            public void onComplete() {
                Log.d(tag,"onComplete");
            }
        };

        observable.subscribe(observer);
    }
}
