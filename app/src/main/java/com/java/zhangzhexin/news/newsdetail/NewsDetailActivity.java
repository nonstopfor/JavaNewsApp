package com.java.zhangzhexin.news.newsdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.java.zhangzhexin.BaseActivity;
import com.java.zhangzhexin.MainActivity;
import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.SingleNews;


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
    private TextView news_title;
    private TextView news_content;
    private TextView news_source;
    private TextView news_date;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail);
        //setContentView(R.layout.news_detail);
        //TODO:news_detail.xml完善
        news_title = findViewById(R.id.news_title);
        news_content = findViewById(R.id.news_content);
        news_source = findViewById(R.id.news_source);
        news_date = findViewById(R.id.news_date);
//        news_title = findViewById(R.id.text_detail);
//        news_content = findViewById(R.id.text_content);

        String news_id = getIntent().getStringExtra("news_id");
//        System.out.println("newdetailacitivity get id = "+news_id);
        myPresenter.setNews(news_id);
        //TODO:调用presenter接口实现 返回一个SingleNews

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    public void setNews(SingleNews news) {
        //news_title.setText("这是一条新闻标题");
        //news_content.setText("这是一条新闻正文");
        news_title.setText(news.title);
        news_content.setText(news.content);
        news_date.setText(news.time);
        news_source.setText(news.source);
    }
}
