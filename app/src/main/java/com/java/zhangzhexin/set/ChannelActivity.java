package com.java.zhangzhexin.set;

import android.content.Context;
import android.content.Intent;

import com.java.zhangzhexin.BaseActivity;

//TODO: 分类增删, 等config做好之后做

public class ChannelActivity extends BaseActivity<ChannelView, ChannelPresenter> implements ChannelView {
    @Override
    public ChannelPresenter createPresenter() {
        return new ChannelPresenter();
    }

    @Override
    public void start(Intent intent) {
        //直接finish退出吧
    }

    @Override
    public Context getMyContext() {
        return this;
    }
}
