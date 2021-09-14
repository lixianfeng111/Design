package com.yhhl.design.base;

import com.yhhl.design.net.LogUtil;
import com.yhhl.design.net.RetrofitUtil;
import java.util.HashMap;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class BaseModel {

    //查询天气
    public void getImage(String url, HashMap<String,Object> map, final BaseCallBack<ResponseBody> callBack){
        BaseService baseService = RetrofitUtil.getInstance().createApi(BaseService.class);
        baseService.getImage(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ResponseBody>(callBack){
                    @Override
                    public void onNext(ResponseBody baseBeans) {
                        super.onNext(baseBeans);
                        callBack.requestSuccess(baseBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        LogUtil.d(e.getMessage());
                    }
                });
    }
}
