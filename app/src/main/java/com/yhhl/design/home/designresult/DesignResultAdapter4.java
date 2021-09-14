package com.yhhl.design.home.designresult;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.design.R;

import java.util.List;

public class DesignResultAdapter4 extends CommonBaseAdapter<String> {

    private Context context;
    private boolean isFirst;
    private int n;
    public DesignResultAdapter4(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
        this.context=context;
    }

    @Override
    protected void convert(ViewHolder holder, String data, int position) {

        holder.setText(R.id.name1,data);
        TextView name1 = holder.getView(R.id.name1);
        if (isFirst&&n==position){
            name1.setTextColor(context.getResources().getColor(R.color.color_3));
        }else {
            name1.setTextColor(context.getResources().getColor(R.color.color_9));
        }
        name1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n=position;
                isFirst=true;
//                if (onChangeChildTextColorListener!=null){
//                    onChangeChildTextColorListener.ChangeTextColorNotify(data,position);
//                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.design_result_item3;
    }

    private OnChangeChildTextColorListener onChangeChildTextColorListener;

    public interface OnChangeChildTextColorListener{
        void ChangeTextColorNotify(String name,int n);
    }

    public void setOnChangeChildTextColorListener(OnChangeChildTextColorListener onChangeChildTextColorListener){
        this.onChangeChildTextColorListener=onChangeChildTextColorListener;
    }
}