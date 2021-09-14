package com.yhhl.design.home.designresult;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.design.R;
import com.yhhl.design.my.adapter.ProjectAdapter;

import java.util.List;

public class DesignResultAdapter1 extends CommonBaseAdapter<String> {

    private Context context;
    private boolean isFirst;
    private int n;
    public DesignResultAdapter1(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
        this.context=context;
    }

    @Override
    protected void convert(ViewHolder holder, String data, int position) {
        holder.setText(R.id.fileName,data);
        holder.getView(R.id.see_the_model).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=position;
                isFirst=true;
                if (onSeeTheModelClickListener!=null){
                    onSeeTheModelClickListener.OnSeeTheModel(data,position);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.design_result_item;
    }

    private OnSeeTheModelClickListener onSeeTheModelClickListener;

    public interface OnSeeTheModelClickListener{
        void OnSeeTheModel(String name,int n);
    }

    public void setOnSeeTheModelClickListener(OnSeeTheModelClickListener onSeeTheModelClickListener){
        this.onSeeTheModelClickListener=onSeeTheModelClickListener;
    }
}