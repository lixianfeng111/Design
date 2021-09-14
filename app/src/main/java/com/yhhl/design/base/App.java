package com.yhhl.design.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.hss01248.dialog.ActivityStackManager;
import com.hss01248.dialog.StyledDialog;
import com.qweather.sdk.view.HeConfig;
import com.yhhl.design.net.LogUtil;
import com.yhhl.design.net.OkHttpUtil;
import com.yhhl.design.util.GlideUtil;
import com.yhhl.design.util.SpzUtils;
import org.litepal.LitePal;

//App类
public class App extends Application {

    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        SpzUtils.init(sContext);//初始化SharedPreferences
        OkHttpUtil.init(sContext);//初始化OkHttp
        LogUtil.init();//初始化日志
        LitePal.initialize(this);//初始化LitePal数据库
        GlideUtil.initGlideHelper(this);//初始化glide
        //初始化百度地图
        HeConfig.init("HE2108261421161092","edc46644b7bb44e3a92947a632e42f27");
        //切换至开发版服务
        HeConfig.switchToDevService();
        initDialogUtils();//初始化DialogUtils
    }

    private void initDialogUtils(){
        //在Application的oncreate方法里:传入context
        StyledDialog.init(this);

//        //在activity生命周期callback中拿到顶层activity引用:
        registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                ActivityStackManager.getInstance().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityStackManager.getInstance().removeActivity(activity);
            }
        });
    }
}