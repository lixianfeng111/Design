package com.yhhl.design.home.drawing.activity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.yhhl.design.R;
import com.yhhl.design.base.BaseActivity;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.util.PickerViewUtil;
import com.yhhl.design.util.SoftKeyboard;
import com.yhhl.design.util.ToastUtil;
import butterknife.BindView;
import butterknife.OnClick;

public class AddDrawingActivity extends BaseActivity implements PickerViewUtil.OnListenerTime {

    @BindView(R.id.title)
    TextView title;

    @BindView(R.id.find)
    TextView find;
    @BindView(R.id.company)
    TextView company;
    @BindView(R.id.project_name)
    TextView project_name;
    @BindView(R.id.book_name)
    TextView book_name;
    @BindView(R.id.start_time)
    TextView start_time;
    @BindView(R.id.content)
    EditText content;
    @BindView(R.id.jie_name)
    EditText jie_name;
    @BindView(R.id.jing_name)
    EditText jing_name;
    @BindView(R.id.site)
    EditText site;
    @BindView(R.id.design_stage)
    EditText design_stage;
    @Override
    public void initView() {
        title.setText("新增图纸");
    }

    @Override
    public void initListener() {
        SoftKeyboard.popSoftKeyboard(this,content);
        SoftKeyboard.popSoftKeyboard(this,jie_name);
        SoftKeyboard.popSoftKeyboard(this,jing_name);
        SoftKeyboard.popSoftKeyboard(this,site);
        SoftKeyboard.popSoftKeyboard(this,design_stage);
        PickerViewUtil pickerViewUtil = new PickerViewUtil();
        pickerViewUtil.setListenerTime(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_add_drawing;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void startTime(String time) {
        start_time.setText(time);
    }

    @Override
    public void endTime(String time) {

    }

    @OnClick({R.id.back, R.id.submit, R.id.find, R.id.project_name, R.id.company, R.id.book_name, R.id.start_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                ToastUtil.showToast("提交成功");
                finish();
                break;
            case R.id.project_name:
                company_name("项目","大连地铁4号一期工程", project_name);
                break;
            case R.id.company:
                company_name("单位","设计单位" , company);
                break;
            case R.id.find:
                company_name("交底查询","" , find);
                break;
            case R.id.book_name:
                company_name("图册名称","设计A02" , book_name);
                break;
            case R.id.start_time:
                PickerViewUtil.initTimePicker(this, start_time);
                break;
        }
    }

    private void company_name(String title, String name,TextView textView) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_accessor, null, false);
        TextView dan1 = view.findViewById(R.id.dan1);
        TextView dan2 = view.findViewById(R.id.dan2);
        TextView dan3 = view.findViewById(R.id.dan3);
        TextView select = view.findViewById(R.id.select);
        select.setText("选择"+title);
        dan1.setText(name+1);
        dan2.setText(name+2);
        dan3.setText(name+3);
        final PopupWindow popupWindow=new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        popupWindow.setContentView(view);
        popupWindow.setFocusable(true);
        setBackgroundAlpha(0.5f);
        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
        //点击外部消失并回复透明度
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
        dan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = dan1.getText().toString();
                textView.setText(s);
//                textView.setTextColor(color);
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });

        dan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = dan2.getText().toString();
                textView.setText(s);
//                textView.setTextColor(color);
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
        dan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = dan3.getText().toString();
                textView.setText(s);
//                textView.setTextColor(color);
                popupWindow.dismiss();
                setBackgroundAlpha(1.0f);
            }
        });
    }

    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
}