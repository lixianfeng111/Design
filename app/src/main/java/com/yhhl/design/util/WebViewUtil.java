package com.yhhl.design.util;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewUtil {
    private static WebView webView2;
//    private static String url="http://114.251.113.1:34668/citybase/explorer/index.html?pmtsUrl=http" +
//            "%3A%2F%2F114.251.113.1%3A34668%2FFreedoMetaSvc%2Fservice%2Fmeta%2Fpmts%2F1.1.0%" +
//            "2FPMTSCapabilities.json%3Ftoken%3DqwbZJfCq7Ls-ijOLj2nAtISMfSQUhuu4v_YZi7_vosI46Pg" +
//            "TEgZlxw7L83qTVeYr7IPE96EQtE31yoQM4SonskvHMabbnv519dhwGkvJ1i1aZJWiV2Opc6yENFVdmlZivXZ" +
//            "6c2k5es7uZc4WxHroQFt4kotZ7giem0q0kVBpNe5_nH4XoKjyrwwS-D68oAE7XDYPgm5I4SvhqjtvEgpu1N4r" +
//            "qdNzC50ZLmGrT2wnoQAf56VAZ1KyMpAFaZ8uyiT37S_4GwmtZt7ya8vRiLJ5xg%3D%3D%26id%3Dd829db9c" +
//            "-5a6f-4541-89aa-c33dcfa4f8a1";

    public static void initWebView(WebView webView, ProgressBar progressBar,String url) {
        /*********WebView的基本设置***********/

        webView2=webView;
        webView2.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        webView2.getSettings().setAllowFileAccess(true);

//        webView.getSettings().setPluginsEnabled(true); // 是否开启插件支持
        webView2.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //-> 按规则重新布局
        webView2.getSettings().setNeedInitialFocus(true); //-> 是否需要获取焦点
        webView2.getSettings().setGeolocationEnabled(false);// -> 设置开启定位功能
        webView2.getSettings().setBlockNetworkLoads(false); //-> 是否从网络获取资源


        webView2.getSettings().setLoadWithOverviewMode(true);//网页自适应问题
        webView2.getSettings().setUseWideViewPort(true); //解决网页自适应问题
        webView2.getSettings().setDomStorageEnabled(false);//DOM Storage
        webView2.getSettings().setJavaScriptEnabled(true);  //设置支持js
        webView2.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        // 支持缩放(适配到当前屏幕)
        webView2.getSettings().setSupportZoom(true);

        // 将图片调整到合适的大小
        // 设置可以被显示的屏幕控制
        webView2.getSettings().setDisplayZoomControls(true);
        webView2.getSettings().setDefaultTextEncodingName("UTF-8");
        webView2.getSettings().setDomStorageEnabled(true);
        webView2.setWebViewClient(new WebViewClient());

        webView2.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setProgress(newProgress);
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setProgress(newProgress);
                }
            }

        });
        webView2.loadUrl(url);
    }
    public static void clearWebView(){
        if (webView2 != null) {
            webView2.stopLoading();
            webView2.clearHistory();
            webView2.clearCache(true);
            webView2.pauseTimers();
            webView2.destroy();
            webView2 = null;
        }
    }
}
