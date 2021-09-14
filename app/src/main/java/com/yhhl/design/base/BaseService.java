package com.yhhl.design.base;

import com.yhhl.design.db.BaseBean;
import com.yhhl.design.weather.bean.HeWeather5;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface BaseService {
    @GET
    Observable<List<BaseBean>> getArea(@Url String url, @QueryMap Map<String, Object> params);

    @GET
    Observable<HeWeather5> getWeather(@Url String url, @QueryMap Map<String, Object> params);
    @GET
    Observable<ResponseBody> getImage(@Url String url, @QueryMap Map<String, Object> params);
}
