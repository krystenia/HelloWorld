package com.example.sf.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import model.QuoteBean;
import utils.Request;

/**
 * Created by 89003337 on 2017/3/22.
 */

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.retrofit)
    Button retrofit;
    @Bind(R.id.rxjava)
    Button rxjava;
    @Bind(R.id.compose)
    Button compose;
    private final String TAG = "HomeActivity";
    private String url="http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1";
//    private String url="http://www.baidu.com";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }



    private void test() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {

            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hello");
                e.onNext("RXJAVA");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext:" + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
        observable.subscribe(observer);
    }

    private void test0(){
        Flowable.range(0, 10)
                .subscribe(new Subscriber<Integer>() {
                    Subscription subscription;

                    //当订阅后，会首先调用这个方法，其实就相当于onStart()，
                    //传入的Subscription s参数可以用于请求数据或者取消订阅
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onsubscribe start");
                        subscription = s;
                        subscription.request(1);
                        Log.d(TAG, "onsubscribe end");
                    }

                    @Override
                    public void onNext(Integer o) {
                        Log.d(TAG, "onNext--->" + o);
                        subscription.request(3);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    private void test1(){
        Observable observable=Observable.create(new ObservableOnSubscribe<QuoteBean>() {
            @Override
            public void subscribe(ObservableEmitter<QuoteBean> e) throws Exception {
                e.onNext(Request.stringRequest(url, com.android.volley.Request.Method.GET));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Observer<QuoteBean> observer = new Observer<QuoteBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "test1 onSubscribe");
            }

            @Override
            public void onNext(QuoteBean s) {
                Log.d(TAG, "onNext:" + s.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
            }
        };
        observable.subscribe(observer);
    }

    @OnClick({R.id.retrofit, R.id.rxjava, R.id.compose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.retrofit:
                break;
            case R.id.rxjava:
                test1();
                break;
            case R.id.compose:
                break;
        }
    }
}
