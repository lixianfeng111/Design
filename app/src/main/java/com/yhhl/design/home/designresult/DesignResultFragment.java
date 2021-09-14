package com.yhhl.design.home.designresult;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.custom.tree.treeview.adapter.TreeAdapter;
import com.android.custom.tree.treeview.entity.TreeEntity;
import com.android.custom.tree.treeview.event.EmptyEvent;
import com.android.custom.tree.treeview.util.JsonUtil;
import com.yhhl.design.R;
import com.yhhl.design.base.BaseFragment;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.event.AddEvent;
import com.yhhl.design.event.DismissEvent;
import com.yhhl.design.home.webview.WebViewConstant;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

public class DesignResultFragment extends BaseFragment  {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.right_icon)
    ImageView right_icon;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.linear1)
    LinearLayout linear1;
    private TreeAdapter treeAdapter;
    @Override
    public void initView() {
        title.setText("设计成果");
        right_icon.setVisibility(View.VISIBLE);
        right_icon.setImageResource(R.mipmap.search);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void initData() {
        String json = JsonUtil.getJson("department2.json", getContext());
        try {
            JSONObject jsonObject = new JSONObject(json);
            TreeEntity treeEntity = new TreeEntity(jsonObject.getString("id"), jsonObject.getString("deptName"), false, jsonObject.getJSONArray("children").length() > 0, jsonObject.getJSONArray("children").toString());
            List<TreeEntity> allValues = new ArrayList<>();
            allValues.add(treeEntity);
            treeAdapter = new TreeAdapter(allValues, getContext());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(treeAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public int initLayoutId() {
        return R.layout.fragment_design_result;
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
                EventBus.getDefault().post(new DismissEvent(new DesignResultFragment()));
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EmptyEvent emptyEvent) {
        WebViewConstant.CATALOG=emptyEvent.getContent();
        EventBus.getDefault().post(new AddEvent(new DesignResultListFragment()));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        treeAdapter.onDestory();
    }

}