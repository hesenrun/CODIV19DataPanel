package com.bqmz001.codiv19panel;

import com.bqmz001.codiv19panel.data.DataSource;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface DataService {
    @GET("https://ncovdata.market.alicloudapi.com/ncov/cityDiseaseInfoWithTrend")
    Observable<DataSource> getData(@Header("Authorization")String appcode);
}
