package com.yhhl.design.fragment;

import android.os.Handler;

import com.google.android.material.tabs.TabLayout;
import com.othershe.baseadapter.interfaces.OnLoadMoreListener;
import com.yhhl.design.R;
import com.yhhl.design.base.BaseFragment;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.message.BackLogFragment;
import com.yhhl.design.message.InformFragment;
import com.yhhl.design.message.MessageAdapter;
import com.yhhl.design.message.WarningMessageFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

public class MessageFragment extends BaseFragment {
    
//    @BindView(R.id.recyclerView)
//    RecyclerView recyclerView;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private MessageAdapter messageAdapter;
    private ArrayList<String> titles;
    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        titles = new ArrayList<>();
        titles.add("通知公告");
        titles.add("待办");
        titles.add("预警消息");
        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new BackLogFragment());
        list.add(new InformFragment());
        list.add(new WarningMessageFragment());

        //预加载
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(list.size());
        //将viewpager与tabLayout绑定
        tab.setupWithViewPager(viewPager);
        for(int i=0;i<titles.size();i++){
            tab.getTabAt(i).setText(titles.get(i));
        }
    }


    @Override
    public int initLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }
}
