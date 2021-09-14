package com.yhhl.design.weather.view;


import com.yhhl.design.base.IBaseView;
import com.yhhl.design.weather.bean.HeWeather5;

public interface WeatherView extends IBaseView<HeWeather5> {
    void OnGetImageSuccess(String imageBean);
}
