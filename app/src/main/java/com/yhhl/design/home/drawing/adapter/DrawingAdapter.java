package com.yhhl.design.home.drawing.adapter;

import android.content.Context;
import android.view.View;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.design.R;
import com.yhhl.design.dialog.DialogUtils;
import com.yhhl.design.event.AddEvent;
import com.yhhl.design.home.designinspection.DesignInspectionAdapter;
import com.yhhl.design.home.designresult.DesignResultFragment;
import com.yhhl.design.home.progressplan.fragment.adapter.ProgressPlanAdapter;
import com.yhhl.design.home.webview.WebViewConstant;
import com.yhhl.design.home.webview.WebViewFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class DrawingAdapter extends CommonBaseAdapter<String> {

    private Context context;
    public DrawingAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
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
        holder.getView(R.id.see_the_model).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==0){
                    WebViewConstant.WebView_URL="http://114.251.113.1:9090/freedobim/#/app/modelPreview?modelId=fd5bed59-92ee-40e2-afe7-af236211e4eb";
                }else if (position==1){
                    WebViewConstant.WebView_URL="http://114.251.113.1:9090/freedobim/#/app/modelPreview?modelId=ce7d2415-70ec-41b3-ba8b-b015773bff33";
                }else {
                    WebViewConstant.WebView_URL="http://114.251.113.1:9090/freedobim/#/app/modelPreview?modelId=bb17b340-81c8-4330-b002-487f632245df";
                }
                WebViewConstant.TITLE="图纸会审";
                EventBus.getDefault().post(new AddEvent(new WebViewFragment()));
            }
        });
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.drawing_item;
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
