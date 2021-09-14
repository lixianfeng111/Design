package com.yhhl.design.home.progressplan.fragment.activity;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.yhhl.design.R;
import com.yhhl.design.base.BaseActivity;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.event.DismissEvent;
import com.yhhl.design.home.drawing.fragment.DrawingFragment;
import com.yhhl.design.home.progressplan.fragment.adapter.PlanOtherAdapter;
import com.yhhl.design.home.progressplan.fragment.adapter.ProgressPlanAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class ProgressPlanOtherActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private PlanOtherAdapter planOtherAdapter;

    @Override
    public void initView() {
        title.setText("项目名称");
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        planOtherAdapter = new PlanOtherAdapter(this, null, true);
        planOtherAdapter.setLoadingView(R.layout.load_loading_layout);
        planOtherAdapter.setLoadFailedView(R.layout.load_failed_layout);
        planOtherAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(planOtherAdapter);
        //延时3s刷新列表
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               List<String> data = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    data.add("item--" + i);
                }
                //刷新数据
                planOtherAdapter.setNewData(data);
            }
        }, 0);
    }

    private void loadMore() {
        planOtherAdapter.setLoadEndView(R.layout.load_end_layout);
        planOtherAdapter.loadEnd();
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_progress_plan_other;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
    @OnClick({R.id.back, R.id.back_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.back_btn:
                finish();
                break;
        }
    }
}