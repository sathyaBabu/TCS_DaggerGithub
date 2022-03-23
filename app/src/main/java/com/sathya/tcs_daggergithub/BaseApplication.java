package com.sathya.tcs_daggergithub;

import android.app.Application;

import com.sathya.tcs_daggergithub.dagger.AppComponent;
import com.sathya.tcs_daggergithub.dagger.DaggerAppComponent;
import com.sathya.tcs_daggergithub.module.AppModule;
import com.sathya.tcs_daggergithub.module.NetworkModule;

import dagger.android.DaggerApplication;

public class BaseApplication  extends Application {
    // happens to be a singleton by default..
    private AppComponent appComponent ;

    @Override
    public void onCreate() {
        super.onCreate();

        // Boiler plate code.. Following code is eliminated by HILT..

        appComponent = DaggerAppComponent.builder()
                .appModule(   new AppModule(this))
                .networkModule(new NetworkModule("https://api.github.com/"))
                .build();

    }
          public AppComponent  getBASENetworkComponent() {
                return appComponent;
    }

}
