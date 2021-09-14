package com.yhhl.design.home.review;

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
import com.yhhl.design.home.drawing.adapter.DrawingAdapter;
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

public class ReviewFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView right_icon;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
//    private DrawingAdapter drawingAdapter;
    private List<String> data;
    private ReviewAdapter reviewAdapter;

    @Override
    public void initView() {
        title.setText("专业评审");
        right_icon.setVisibility(View.VISIBLE);
        right_icon.setImageResource(R.drawable.add);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        reviewAdapter = new ReviewAdapter(getContext(), null, true);
        reviewAdapter.setLoadingView(R.layout.load_loading_layout);
        reviewAdapter.setLoadFailedView(R.layout.load_failed_layout);
        reviewAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });
        reviewAdapter.setOnDeleteListener(new ReviewAdapter.OnDeleteListener() {
            @Override
            public void OnDeleteListener(int position) {
                data.remove(position);
                reviewAdapter.setNewData(data);
                reviewAdapter.loadEnd();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(reviewAdapter);
        //延时3s刷新列表
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                data = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    data.add("item--" + i);
                }
                //刷新数据
                reviewAdapter.setNewData(data);
            }
        }, 0);
    }

    private void loadMore() {
        reviewAdapter.setLoadEndView(R.layout.load_end_layout);
        reviewAdapter.loadEnd();
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_review;
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
                EventBus.getDefault().post(new DismissEvent(new ReviewFragment()));
                break;
            case R.id.right_icon:
                IntentUtil.getInstance().intent(getContext(), AddPlanActivity.class,null);
                break;
        }
    }

}
