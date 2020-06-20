package com.bqmz001.codiv19panel;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataRepository {
    public static Observable getData(String appcode) {
        return new Retrofit.Builder()
                .baseUrl("https://ncovdata.market.alicloudapi.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(DataService.class)
                .getData("APPCODE " + appcode);
    }
}
