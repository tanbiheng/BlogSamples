package com.tbh.app.base;

import android.app.Application;

/**
 * Created by tanbiheng on 2018/1/24.
 */

public class MyApplication extends Application {

    private static MyApplication INSTANCE = null;

    public static Application getInstance(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
    }
}
