package com.sathya.tcs_daggergithub.module;


import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    String BASE_URL ;

    public NetworkModule( String baseUrl){ this.BASE_URL = baseUrl; }

    @Provides
    @Singleton
    Gson provideGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return  gsonBuilder.create();
    }

    // Assignment  is to provide the Okhttp to retrofit..
    // OKhttp


    // Gson is automatically picked from the Dagger Graph table??????
    @Provides
    @Singleton

    Retrofit provideRetrofit( Gson gson ){

        return  new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();


    }


}
