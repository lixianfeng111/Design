package com.yhhl.design.login;

import butterknife.BindView;
import butterknife.OnClick;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.yhhl.design.MainActivity;
import com.yhhl.design.R;
import com.yhhl.design.base.BaseActivity;
import com.yhhl.design.base.BasePresenter;
import com.yhhl.design.statusBar.StatusManager;
import com.yhhl.design.util.GlideUtil;
import com.yhhl.design.util.IntentUtil;
import com.yhhl.design.util.SoftKeyboard;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login_logo)
    ImageView login_logo;

    @Override
    public void initView() {
        StatusManager.getInstance().immersionStatusBar(this,false);
    }

    @Override
    public void initListener() {
        SoftKeyboard.popSoftKeyboard(this,userName);
        SoftKeyboard.popSoftKeyboard(this,password);
    }

    @Override
    public void initData() {
        GlideUtil.showImage(login_logo,R.drawable.login_logo,2, null);
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initVariable() {

    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                String s = userName.getText().toString();
                String s1 = password.getText().toString();
                IntentUtil.getInstance().intent(this, MainActivity.class,null);
                finish();
                break;
        }
    }
}