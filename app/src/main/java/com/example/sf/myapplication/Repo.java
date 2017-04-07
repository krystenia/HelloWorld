package com.example.sf.myapplication;

/**
 * Created by 89003337 on 2017/4/6.
 */

public class Repo {
    public String name; // 库的名字
    public String description; // 描述
    public String language; // 语言

    @Override
    public String toString() {
        return "Repo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
