package com.yhhl.design.home.review;

import android.content.Context;
import android.view.View;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.design.R;
import com.yhhl.design.dialog.DialogUtils;
import java.util.List;

public class ReviewAdapter extends CommonBaseAdapter {

    private Context context;
    public ReviewAdapter(Context context, List datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
        this.context=context;
    }

    @Override
    protected void convert(ViewHolder holder, Object data, int position) {
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
        return R.layout.review_item;
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
