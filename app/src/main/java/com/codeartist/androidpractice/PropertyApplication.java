package com.codeartist.androidpractice;

import android.app.Application;

public class PropertyApplication extends Application {
    private static Application sApp;
    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }
    public static Application getInstance(){
        return sApp;

    }

}
