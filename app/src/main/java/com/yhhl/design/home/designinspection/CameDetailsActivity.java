package com.yhhl.design.home.designinspection;

import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.yhhl.design.R;
import com.yhhl.design.base.BaseActivity;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.event.DismissEvent;
import com.yhhl.design.home.drawing.activity.AddDrawingActivity;
import com.yhhl.design.home.drawing.fragment.DrawingFragment;
import com.yhhl.design.util.IntentUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class CameDetailsActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private CameDetailsAdapter cameDetailsAdapter;

    @Override
    public void initView() {
        title.setText("会审详情");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        cameDetailsAdapter = new CameDetailsAdapter(this, null, true);
        cameDetailsAdapter.setLoadingView(R.layout.load_loading_layout);
        cameDetailsAdapter.setLoadFailedView(R.layout.load_failed_layout);
        cameDetailsAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(cameDetailsAdapter);
        //延时3s刷新列表
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> data = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    data.add("item--" + i);
                }
                //刷新数据
                cameDetailsAdapter.setNewData(data);
            }
        }, 0);
    }

    private void loadMore() {
        cameDetailsAdapter.setLoadEndView(R.layout.load_end_layout);
        cameDetailsAdapter.loadEnd();
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_come_details;
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
                finish();
                break;
        }
    }
}
