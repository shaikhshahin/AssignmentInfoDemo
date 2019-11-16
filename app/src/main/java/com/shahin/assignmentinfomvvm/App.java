package com.shahin.assignmentinfomvvm;

import android.app.Application;


/**
 * Created by Shahin on 16/11/2019.
 */
public class App extends Application {

    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App getInstance() {
        return sInstance;
    }

}
