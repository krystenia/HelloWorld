package com.example.sf.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import utils.IQuote;
import utils.Request;
import utils.RequestManager;

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
    private String url = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1";
    private String baseUrl = "http://quotesondesign.com/";
    //    private String url="http://www.baidu.com";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }


    /**
     * a simple sample of rxjava
     */
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

    /**
     * rxjava flowable Object
     */
    private void test0() {
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

    /**
     * use rxjava and volley to get remote data
     */
    private void test1() {
        Observable observable = Observable.create(new ObservableOnSubscribe<QuoteBean>() {
            @Override
            public void subscribe(ObservableEmitter<QuoteBean> e) throws Exception {
                e.onNext(Request.stringRequest(url, com.android.volley.Request.Method.GET, HomeActivity.class.getSimpleName()));
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

    /**
     * use retrofit to get remote data
     */
    private void test2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IQuote iQuote = retrofit.create(IQuote.class);
        //set request params
        Call<List<QuoteBean>> call = iQuote.getQuote("rand", "1");
        call.enqueue(new Callback<List<QuoteBean>>() {
            @Override
            public void onResponse(Call<List<QuoteBean>> call, Response<List<QuoteBean>> response) {
                List<QuoteBean> quotes = response.body();
                Log.d(TAG + "onresponse", quotes.get(0).toString());
            }

            @Override
            public void onFailure(Call<List<QuoteBean>> call, Throwable t) {
                Log.d(TAG + "onFailure", "");
            }
        });
    }

    /**
     * rxjava + retrofit
     */
    private void test3() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        IQuote iQuote = retrofit.create(IQuote.class);
        final Call<List<QuoteBean>> call = iQuote.getQuote("rand", "1");
        Observable<List<QuoteBean>> observable = Observable.create(new ObservableOnSubscribe<List<QuoteBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<QuoteBean>> e) throws Exception {
                e.onNext(call.execute().body());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        Observer observer = new Observer() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {

                Log.e(TAG, o.toString());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onerror");
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    @OnClick({R.id.retrofit, R.id.rxjava, R.id.compose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.retrofit:
                test2();
                break;
            case R.id.rxjava:
                test1();
                break;
            case R.id.compose:
                test3();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //when this activity call ondestroy,cancel the request in requestQueue
        RequestManager.getRequestQueue().cancelAll(HomeActivity.class.getSimpleName());
    }

}
