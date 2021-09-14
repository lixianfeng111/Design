package com.yhhl.design.home.designchange;

import android.content.Context;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.yhhl.design.R;
import com.yhhl.design.base.BaseFragment;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.event.DismissEvent;
import com.yhhl.design.home.progressplan.fragment.activity.AddPlanActivity;
import com.yhhl.design.util.IntentUtil;
import com.yhhl.design.util.SoftKeyboard;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class DesignChangeFragment extends BaseFragment {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.search)
    EditText search;
    @BindView(R.id.right_icon)
    ImageView right_icon;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<String> data;
    private DesignChangeAdapter designChangeAdapter;

    @Override
    public void initView() {
        title.setText("设计变更");
        right_icon.setVisibility(View.VISIBLE);
        right_icon.setImageResource(R.drawable.add);
    }

    @Override
    public void initListener() {
//        SoftKeyboard.popSoftKeyboard(getActivity(),search);

    }

    @Override
    public void initData() {
        designChangeAdapter = new DesignChangeAdapter(getContext(), null, true);
        designChangeAdapter.setLoadingView(R.layout.load_loading_layout);
        designChangeAdapter.setLoadFailedView(R.layout.load_failed_layout);
        designChangeAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                loadMore();
            }
        });
        designChangeAdapter.setOnDeleteListener(new DesignChangeAdapter.OnDeleteListener() {
            @Override
            public void OnDeleteListener(int position) {
                data.remove(position);
                designChangeAdapter.setNewData(data);
                designChangeAdapter.loadEnd();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(designChangeAdapter);
        //延时3s刷新列表
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                data = new ArrayList<>();
                for (int i = 0; i < 2; i++) {
                    data.add("item--" + i);
                }
                //刷新数据
                designChangeAdapter.setNewData(data);
            }
        }, 0);
    }

    private void loadMore() {
        designChangeAdapter.setLoadEndView(R.layout.load_end_layout);
        designChangeAdapter.loadEnd();
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_design_change;
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
                EventBus.getDefault().post(new DismissEvent(new DesignChangeFragment()));
                break;
            case R.id.right_icon:
                IntentUtil.getInstance().intent(getContext(), AddPlanActivity.class,null);
                break;
        }
    }
}
