package com.java.zhangzhexin.set;

import android.content.Context;
import android.content.Intent;

import com.java.zhangzhexin.BaseActivity;

//TODO: 分类增删, 等config做好之后做

public class SetActivity extends BaseActivity<SetView,SetPresenter> implements SetView {
    @Override
    public SetPresenter createPresenter() {
        return null;
    }

    @Override
    public void start(Intent intent) {

    }

    @Override
    public Context getMyContext() {
        return null;
    }
}
