package com.yhhl.design.home.designresult;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.yhhl.design.R;
import com.yhhl.design.base.BaseFragment;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.event.AddEvent;
import com.yhhl.design.event.DismissEvent;
import com.yhhl.design.home.review.ReviewAdapter;
import com.yhhl.design.home.webview.WebViewConstant;
import com.yhhl.design.home.webview.WebViewFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class DesignResultListFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<String> data;
    private List<String> url;
    private DesignResultAdapter1 designResultAdapter1;
    private int n;
    @Override
    public void initView() {
        title.setText("设计成果");
    }

    @Override
    public void initListener() {
        data = new ArrayList<>();
        url = new ArrayList<>();
        if (WebViewConstant.CATALOG.contains("专项设计")){
            data.add("车站管综DLM4-SS-C03-GZ");
            url.add("http://114.251.113.1:9090/freedobim/#/app/modelPreview?modelId=eb208bf9-d1c6-4b46-b90c-96392c8020c6");
            data.add("车站建筑DLM4-SS-C03-JZ");
            url.add("http://114.251.113.1:9090/freedobim/#/app/modelPreview?modelId=913d6208-667a-4664-b36c-0ffe27436863");
            n=0;
        }else if(WebViewConstant.CATALOG.contains("车站建筑")){
            data.add("DLM4-SS-C01-JZ-2020");
            url.add("http://114.251.113.1:9090/freedobim/#/app/modelPreview?modelId=fd5bed59-92ee-40e2-afe7-af236211e4eb");
            data.add("DLM4-SS-C01-JG-FS-2020");
            url.add("http://114.251.113.1:9090/freedobim/#/app/modelPreview?modelId=ce7d2415-70ec-41b3-ba8b-b015773bff33");
            data.add("DLM4-SS-C01-JG-ZT-2020");
            url.add("http://114.251.113.1:9090/freedobim/#/app/modelPreview?modelId=ce7d2415-70ec-41b3-ba8b-b015773bff33");
            n=1;
        }

    }

    @Override
    public void initData() {
        designResultAdapter1 = new DesignResultAdapter1(getContext(), data, true);
        designResultAdapter1.setLoadingView(R.layout.load_loading_layout);
        designResultAdapter1.setLoadFailedView(R.layout.load_failed_layout);
        designResultAdapter1.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });

        designResultAdapter1.setOnSeeTheModelClickListener(new DesignResultAdapter1.OnSeeTheModelClickListener() {
            @Override
            public void OnSeeTheModel(String name, int n) {
                WebViewConstant.TITLE="设计成果";
                WebViewConstant.WebView_URL=url.get(n);
                EventBus.getDefault().post(new AddEvent(new WebViewFragment()));
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(designResultAdapter1);
        //延时3s刷新列表
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                data = new ArrayList<>();
//                for (int i = 0; i < 2; i++) {
//                    data.add("item--" + i);
//                }
//                //刷新数据
//                designResultAdapter1.setNewData(data);
//            }
//        }, 0);
    }

    private void loadMore() {
        designResultAdapter1.setLoadEndView(R.layout.load_end_layout);
        designResultAdapter1.loadEnd();
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_design_result_list;
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
                EventBus.getDefault().post(new DismissEvent(new DesignResultListFragment()));
                break;
        }
    }
}
