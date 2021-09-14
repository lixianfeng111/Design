package com.yhhl.design.home.countersign;

import android.content.Context;
import android.view.View;

import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.design.R;
import com.yhhl.design.dialog.DialogUtils;


import java.util.List;

public class CountersignAdapter extends CommonBaseAdapter<String> {

    private Context context;
    public CountersignAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
        this.context=context;
    }

    @Override
    protected void convert(ViewHolder holder, String data, int position) {
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
        return R.layout.countersign_item;
    }

    private OnDeleteListener onDeleteListener;
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
