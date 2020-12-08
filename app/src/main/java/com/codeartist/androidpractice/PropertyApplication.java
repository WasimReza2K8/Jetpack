package com.codeartist.androidpractice;

import android.app.Application;

import com.codeartist.androidpractice.repository.Repository;

public class PropertyApplication extends Application {
    public Repository repository;
    @Override
    public void onCreate() {
        super.onCreate();
        //repository = Repository.getInstance(this);
    }

}
