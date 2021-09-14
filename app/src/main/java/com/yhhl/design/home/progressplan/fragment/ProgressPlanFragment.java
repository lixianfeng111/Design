package com.yhhl.design.home.progressplan.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.yhhl.design.R;
import com.yhhl.design.base.BaseFragment;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.event.DismissEvent;
import com.yhhl.design.home.progressplan.fragment.activity.AddPlanActivity;
import com.yhhl.design.home.progressplan.fragment.activity.ProgressPlanOtherActivity;
import com.yhhl.design.home.progressplan.fragment.adapter.ProgressPlanAdapter;
import com.yhhl.design.util.IntentUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class ProgressPlanFragment extends BaseFragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView right_icon;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private ProgressPlanAdapter progressPlanAdapter;
    private List<String> data;
    @Override
    public void initView() {
        title.setText("进度计划");
        right_icon.setVisibility(View.VISIBLE);
        right_icon.setImageResource(R.drawable.add);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        progressPlanAdapter = new ProgressPlanAdapter(getContext(), null, true);
        progressPlanAdapter.setLoadingView(R.layout.load_loading_layout);
        progressPlanAdapter.setLoadFailedView(R.layout.load_failed_layout);
        progressPlanAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });

        progressPlanAdapter.setOnOtherListener(new ProgressPlanAdapter.OnOtherListener() {
            @Override
            public void OnOtherListener() {
                IntentUtil.getInstance().intent(getContext(), ProgressPlanOtherActivity.class,null);
            }
        });

        progressPlanAdapter.setOnDeleteListener(new ProgressPlanAdapter.OnDeleteListener() {
            @Override
            public void OnDeleteListener(int position) {
                data.remove(position);
                progressPlanAdapter.setNewData(data);
                progressPlanAdapter.loadEnd();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(progressPlanAdapter);
        //延时3s刷新列表
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                data = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    data.add("item--" + i);
                }
                //刷新数据
                progressPlanAdapter.setNewData(data);
            }
        }, 0);
    }

    public interface Callback {
        void onDialogClick();
    }

    private void loadMore() {
        progressPlanAdapter.setLoadEndView(R.layout.load_end_layout);
        progressPlanAdapter.loadEnd();
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_progress_plan;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.back, R.id.right_icon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                EventBus.getDefault().post(new DismissEvent(new ProgressPlanFragment()));
                break;
            case R.id.right_icon:
                IntentUtil.getInstance().intent(getContext(), AddPlanActivity.class,null);
                break;
        }
    }
}
