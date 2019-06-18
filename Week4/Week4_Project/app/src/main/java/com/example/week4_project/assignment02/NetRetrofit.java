package com.example.week4_project.assignment02;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofit {

    private static NetRetrofit ourInstance = new NetRetrofit();
    public static NetRetrofit getInstance(){
        return ourInstance;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://45.32.46.136:8001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    RetrofitExService service = retrofit.create(RetrofitExService.class);

    public RetrofitExService getService(){
        return service;
    }
}
