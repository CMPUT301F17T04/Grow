package com.grow.cmputf17team4.grow.Models;

import android.app.Application;
import android.content.Context;

/**
 * Created by qin7 on 2017/11/12.
 */

public class App extends Application {
    private static App instance;

    public static Context getContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
