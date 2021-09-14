package com.yhhl.design.home.designinspection;

import android.content.Context;
import android.view.View;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.design.R;

import com.yhhl.design.dialog.DialogUtils;
import com.yhhl.design.home.progressplan.fragment.ProgressPlanFragment;
import com.yhhl.design.home.progressplan.fragment.adapter.ProgressPlanAdapter;

import java.util.List;

public class DesignInspectionAdapter extends CommonBaseAdapter<String> {

    private Context context;
    public DesignInspectionAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
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
        return R.layout.design_inspection_item;
    }

    private ProgressPlanAdapter.OnOtherListener onOtherListener;
    private ProgressPlanAdapter.OnDeleteListener onDeleteListener;

    public interface OnOtherListener{
        void OnOtherListener();
    }

    public void setOnOtherListener(ProgressPlanAdapter.OnOtherListener onOtherListener){
        this.onOtherListener=onOtherListener;
    }

    public interface OnDeleteListener{
        void OnDeleteListener(int position);
    }

    public void setOnDeleteListener(ProgressPlanAdapter.OnDeleteListener onDeleteListener){
        this.onDeleteListener=onDeleteListener;
    }
    public interface Callback {
        void onDialogClick();
    }
}
