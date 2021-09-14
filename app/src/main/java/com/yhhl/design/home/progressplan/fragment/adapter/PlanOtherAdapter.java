package com.yhhl.design.home.progressplan.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.design.R;

import java.util.List;

public class PlanOtherAdapter extends CommonBaseAdapter<String> {

    private boolean isUnfold;
    public PlanOtherAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, String data, int position) {
        if (position==0){
            holder.setText(R.id.stage,"第一阶段");
        }else {
            holder.setText(R.id.stage,"第二阶段");
        }

        ImageView down_or_up = holder.getView(R.id.down_or_up);
        LinearLayout linear = holder.getView(R.id.linear);
        RelativeLayout rela = holder.getView(R.id.rela);
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUnfold){//展示
                    isUnfold=false;
                    down_or_up.setImageResource(R.mipmap.up_icon);
                    rela.setVisibility(View.VISIBLE);
                }else {//收起
                    isUnfold=true;
                    down_or_up.setImageResource(R.mipmap.down_icon);
                    rela.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.plan_other_item;
    }
}