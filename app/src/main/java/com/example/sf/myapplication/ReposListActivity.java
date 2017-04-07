package com.example.sf.myapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 89003337 on 2017/4/6.
 */

public class ReposListActivity extends BaseActivity {
    @Bind(R.id.repos_rv_list)
    RecyclerView mRvList;

    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;

    @Inject
    GithubApiService githubApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    @Override
    public int getLayoutId(){
        return R.layout.activity_repos_list;
    }

    @Override
    protected void init() {

    }

    @Override
    public void setupActivityComponent(AppComponent appComponent){
        appComponent.inject(this);
    }


    private void initView(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);

        ListAdapter adapter = new ListAdapter();
        mRvList.setAdapter(adapter);
        loadData(adapter);
    }

    private void loadData(final ListAdapter adapter){
        showLoading(true);
        githubApiService.getRepoData(getUser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList<Repo>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("ReposListActivity","onSubscribe");
                    }

                    @Override
                    public void onNext(ArrayList<Repo> repos) {
                        showLoading(false);
                        adapter.setRepos(repos);
                        Log.d("ReposListActivity","onNext"+repos);
                    }
                    @Override
                    public void onError(Throwable e){
                        showLoading(false);
                        Log.d("ReposListActivity","onError");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private String getUser(){
        return "bird1015";
    }

    public void showLoading(boolean loading) {
//        Log.i("info",loading + " Repos");
        pbLoading.setVisibility(loading ? View.VISIBLE : View.GONE);
    }
}
