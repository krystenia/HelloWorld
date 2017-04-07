package com.example.sf.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.button)
    Button button;
    @Bind(R.id.username)
    EditText username;
    @Bind(R.id.password)
    EditText password;
    @Bind(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    protected void init() {
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.button})
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.button:
                String name = username.getText().toString();
                String pwd = password.getText().toString();
//        if(TextUtils.isEmpty(name)){
//            Toast.makeText(this,"username must not be empty",Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if(TextUtils.isEmpty(pwd)){
//            Toast.makeText(this,"password must not be empty",Toast.LENGTH_SHORT).show();
//            return;
//        }
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}
