package com.yhhl.design.home.drawing.fragment;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.yhhl.design.R;
import com.yhhl.design.base.BaseFragment;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.event.DismissEvent;
import com.yhhl.design.home.designinspection.CameDetailsActivity;
import com.yhhl.design.home.drawing.activity.AddDrawingActivity;
import com.yhhl.design.home.drawing.adapter.DrawingAdapter;
import com.yhhl.design.home.progressplan.fragment.ProgressPlanFragment;
import com.yhhl.design.home.progressplan.fragment.activity.AddPlanActivity;
import com.yhhl.design.home.progressplan.fragment.adapter.ProgressPlanAdapter;
import com.yhhl.design.util.IntentUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class DrawingFragment extends BaseFragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView right_icon;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private DrawingAdapter drawingAdapter;
    private List<String> data;
    @Override
    public void initView() {
        title.setText("图纸会审");
        right_icon.setVisibility(View.VISIBLE);
        right_icon.setImageResource(R.drawable.add);
    }

    @Override
    public void initListener() {
        data = new ArrayList<>();
        data.add("DLM4-SS-C01-JZ-2020");
        data.add("DLM4-SS-C01-JZ-2020");
        data.add("DLM4-SS-C01-JG-ZT-2020");
    }

    @Override
    public void initData() {
        drawingAdapter = new DrawingAdapter(getContext(), data, true);
        drawingAdapter.setLoadingView(R.layout.load_loading_layout);
        drawingAdapter.setLoadFailedView(R.layout.load_failed_layout);
        drawingAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });
        drawingAdapter.setOnDeleteListener(new DrawingAdapter.OnDeleteListener() {
            @Override
            public void OnDeleteListener(int position) {
                data.remove(position);
                drawingAdapter.setNewData(data);
                drawingAdapter.loadEnd();
            }
        });
        drawingAdapter.setOnOtherListener(new DrawingAdapter.OnOtherListener() {
            @Override
            public void OnOtherListener() {
                IntentUtil.getInstance().intent(getContext(), CameDetailsActivity.class,null);
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(drawingAdapter);
//        //延时3s刷新列表
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                data = new ArrayList<>();
//                for (int i = 0; i < 2; i++) {
//                    data.add("item--" + i);
//                }
//                //刷新数据
//                drawingAdapter.setNewData(data);
//            }
//        }, 0);
    }

    private void loadMore() {
        drawingAdapter.setLoadEndView(R.layout.load_end_layout);
        drawingAdapter.loadEnd();
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_drawing;
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
                EventBus.getDefault().post(new DismissEvent(new DrawingFragment()));
                break;
            case R.id.right_icon:
                IntentUtil.getInstance().intent(getContext(), AddDrawingActivity.class,null);
                break;
        }
    }
}