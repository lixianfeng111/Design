package com.yhhl.design.util;

import android.widget.Toast;
import com.yhhl.design.base.App;


public class ToastUtil {
    public static void showToast(String content) {
        //判空
        Toast.makeText(App.sContext, content, Toast.LENGTH_SHORT).show();
    }
}
