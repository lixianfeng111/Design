package com.yhhl.design.fragment;

import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.yhhl.design.R;
import com.yhhl.design.base.BaseFragment;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.event.EmptyEvent;
import com.yhhl.design.util.GlideUtil;
import com.yhhl.design.util.MyDialogFragment;
import com.yhhl.design.util.SpzUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class MyFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.headPic)
    ImageView headPic;
    private ArrayList<String> titles;
    @Override
    public void initView() {
        title.setText("我的");
        back.setVisibility(View.GONE);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        GlideUtil.showImage(headPic,R.drawable.headpic,2, null);
    }

    @Override
    public int initLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initVariable() {

    }


    @Override
    public BasePresenter initPresenter() {
        return null;
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void project_name(EmptyEvent emptyEvent){
        String name_o = SpzUtils.getString("name_o");
        String name_p = SpzUtils.getString("name_p");
        name.setText(name_o+name_p);
    }

    @OnClick({R.id.project})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.project:
                MyDialogFragment.newInstance().show(getChildFragmentManager(), "dialogTag2");
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(getContext())){
            EventBus.getDefault().unregister(this);
        }
    }
}