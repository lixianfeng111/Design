package com.yhhl.design.home.progressplan.fragment.adapter;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.design.R;
import com.yhhl.design.dialog.DialogUtils;

import java.util.List;

public class ProgressPlanAdapter extends CommonBaseAdapter<String> {

    private List<String> datas;
    private Context context;
    private LinearLayout progress_plan;

    public ProgressPlanAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
        this.datas=datas;
        this.context=context;
    }

    @Override
    protected void convert(ViewHolder holder, String data, int position) {
        holder.getView(R.id.other).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onOtherListener!=null){
                    onOtherListener.OnOtherListener();
                }
            }
        });
        holder.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.showDefaultAlertDialog("是否删除该条信息", new DialogUtils.ClickListener() {
                    @Override
                    public void clickConfirm() {
                        if (onDeleteListener!=null){
                            onDeleteListener.OnDeleteListener(position);
                        }
                    }
                });
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.progress_plan_item;
    }

    private OnOtherListener onOtherListener;
    private OnDeleteListener onDeleteListener;

    public interface OnOtherListener{
        void OnOtherListener();
    }

    public void setOnOtherListener(OnOtherListener onOtherListener){
        this.onOtherListener=onOtherListener;
    }

    public interface OnDeleteListener{
        void OnDeleteListener(int position);
    }

    public void setOnDeleteListener(OnDeleteListener onDeleteListener){
        this.onDeleteListener=onDeleteListener;
    }

    public interface Callback {
        void onDialogClick();
    }
}