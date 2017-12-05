package com.grow.cmputf17team4.grow.Models;

import android.app.Application;
import android.content.Context;

/**
 * Class provide a context for the entire app
 * @author Qin Zhang
 */

public class App extends Application {
    private static App instance;

    public static Context getContext(){
        return instance.getApplicationContext();
    }
    /**
     * OnCreate method
     */
    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
