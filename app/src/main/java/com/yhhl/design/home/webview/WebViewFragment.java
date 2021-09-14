package com.yhhl.design.home.webview;

import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yhhl.design.R;
import com.yhhl.design.base.BaseFragment;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.event.DismissEvent;
import com.yhhl.design.util.WebViewUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class WebViewFragment extends BaseFragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.probar)
    ProgressBar probar;
    @Override
    public void initView() {
        title.setText(WebViewConstant.TITLE);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        WebViewUtil.initWebView(webView,probar, WebViewConstant.WebView_URL);
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                EventBus.getDefault().post(new DismissEvent(new WebViewFragment()));
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.stopLoading();
            webView.clearHistory();
            webView.clearCache(true);
            webView.pauseTimers();
            webView.destroy();
            webView = null;
            WebViewUtil.clearWebView();
        }
    }
}
