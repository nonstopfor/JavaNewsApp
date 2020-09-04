package com.java.zhangzhexin.news.newsdetail;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.java.zhangzhexin.BaseActivity;
import com.java.zhangzhexin.R;


/*
FIXME:
感觉详情页不需要fragment的切换
可以根据不同type 展开不同的type_detail.xml?
可能参数有些不同 具体处理
可以定义
一个abstract的参数提取
一个abstract的参数设置函数

先写完News的详情页 之后再决定如何扩展到其他的详情页
*/
public class NewsDetailActivity extends BaseActivity<NewsDetailView,NewsDetailPresenter> implements NewsDetailView  {
    @Override
    public NewsDetailPresenter createPresenter() {
        return null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

    }
}
