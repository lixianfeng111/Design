package com.yhhl.design.home.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.othershe.baseadapter.ViewHolder;
import com.othershe.baseadapter.base.CommonBaseAdapter;
import com.yhhl.design.R;
import com.yhhl.design.home.progressplan.fragment.adapter.ProgressPlanAdapter;
import com.yhhl.design.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends CommonBaseAdapter<String> {

    private boolean isPICK_UP=false;
    private List<String> datas1=new ArrayList<>();
    private List<String> datas2=new ArrayList<>();
    public static String name_title;
    public HomeAdapter(Context context, List<String> datas, boolean isOpenLoadMore) {
        super(context, datas, isOpenLoadMore);
        this.datas1=datas;
        datas1.remove(9);
        datas1.remove(9);
    }

    @Override
    protected void convert(ViewHolder holder, String data, int position) {
//        if (position>datas1.size()-1){
//            return;
//        }
        LinearLayout linear = holder.getView(R.id.linear);
        holder.setText(R.id.module_title,datas1.get(position));
        ImageView image = holder.getView(R.id.image);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==datas1.size()-1){
                    if (isPICK_UP){
                        datas1.remove(9);
                        datas1.remove(9);
                        isPICK_UP=false;
                    }else {
                        datas1.add("可视化场布"+"\n");
                        datas1.add("收起"+"\n");
                        isPICK_UP=true;
                    }
                    notifyDataSetChanged();
                }else {
                    if (positionListener!=null){
                        positionListener.OnClickPositionListener(position);
                    }
                }
            }
        });

        holder.getView(R.id.module_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position==datas1.size()-1){
                    if (isPICK_UP){
                        datas1.remove(9);
                        datas1.remove(9);
                        isPICK_UP=false;
                    }else {
                        datas1.add("可视化场布"+"\n");
                        datas1.add("收起"+"\n");
                        isPICK_UP=true;
                    }
                    notifyDataSetChanged();
                }else {
                    if (positionListener!=null){
                        positionListener.OnClickPositionListener(position);
                    }
                }
            }
        });


        switch (position){
            case 0:
                image.setImageResource(R.mipmap.design_result);
                break;
            case 1:
                image.setImageResource(R.mipmap.progress_plan);
                break;
            case 2:
                image.setImageResource(R.mipmap.drawing_review_sheet);
                break;
            case 3:
                image.setImageResource(R.mipmap.drawing);
                break;
            case 4:
                image.setImageResource(R.mipmap.scheme_appraisal);
                break;
            case 5:
                image.setImageResource(R.mipmap.infor_to_each_other);
                break;
            case 6:
                image.setImageResource(R.mipmap.file);
                break;
            case 7:
                image.setImageResource(R.mipmap.countersign);
                break;
            case 8:
                image.setImageResource(R.mipmap.alteration);
                break;
            case 9:
                if (isPICK_UP){
                    name_title="征拆信息";
                    image.setImageResource(R.mipmap.open_information);
                    holder.setText(R.id.module_title,"征拆信息"+"\n");
                }else {
                    name_title="展开";
                    image.setImageResource(R.mipmap.unfold);
                    holder.setText(R.id.module_title,"展开"+"\n");
                }
                break;
            case 10:
                image.setImageResource(R.mipmap.visual_market);
                break;
            case 11:
                image.setImageResource(R.mipmap.pick_up);
                holder.setText(R.id.module_title,"收起"+"\n");
                break;
        }
    }

    @Override
    protected int getItemLayoutId() {
        return R.layout.home_item;
    }

    private OnClickPositionListener positionListener;

    public interface OnClickPositionListener{
        void OnClickPositionListener(int position);
    }

    public void setOnClickPositionListener(OnClickPositionListener positionListener){
        this.positionListener=positionListener;
    }
}