package com.example.sf.myapplication;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by 89003337 on 2017/4/7.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
