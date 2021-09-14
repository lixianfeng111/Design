package com.yhhl.design.weather.presenter;

import com.yhhl.design.base.BaseCallBack;
import com.yhhl.design.base.BaseModel;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.base.ServerException;
import com.yhhl.design.weather.bean.HeWeather5;
import com.yhhl.design.weather.view.WeatherView;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;

public class WeatherPresenter extends BasePresenter<WeatherView> {

    private final BaseModel baseModel;

    public WeatherPresenter(WeatherView iBaseView) {
        super(iBaseView);
        baseModel = new BaseModel();
    }

    public void getImage(String s){
        HashMap<String, Object> map = new HashMap<>();
        baseModel.getImage(s, map, new BaseCallBack<ResponseBody>() {

            @Override
            public void requestError(ServerException e) {
            }

            @Override
            public void requestSuccess(ResponseBody imageBean) {
                try {
                    String string = imageBean.string();
                    getiBaseView().OnGetImageSuccess(string);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

}
