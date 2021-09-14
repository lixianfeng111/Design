package com.yhhl.design.home.designinspection;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.design.R;

import java.util.List;

public class CameDetailsAdapter extends CommonBaseAdapter<String> {
    private boolean isUnfold;
    public CameDetailsAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
    }

    @Override
    protected void convert(ViewHolder holder, String data, int position) {
        if (position==0){
            holder.setText(R.id.stage,"第一阶段");
        }else {
            holder.setText(R.id.stage,"第二阶段");
            holder.setText(R.id.problem,"会审问题：桩基尺寸与总平面图不一致，请核实一哪张图为准");
            holder.setText(R.id.person_in_charge,"答复意见：以总平面图为准");
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
        return R.layout.came_details_item;
    }
}
