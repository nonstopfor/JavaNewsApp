package com.java.zhangzhexin.detail.newsdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.java.zhangzhexin.BaseFragment;
import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.SingleNews;

public class NewsDetailFragment extends BaseFragment<NewsDetailView,NewsDetailPresenter> implements NewsDetailView{
    private TextView news_title;
    private TextView news_content;
    private TextView news_source;
    private TextView news_date;
    private String news_id;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("ListFragment : "+type+" onCreateView");
        if(view == null)
        {
            System.out.println("ListFragment: "+type+" view为空");
            view = inflater.inflate(R.layout.news_detail, container, false);
            initView();
        }
        return view;
    }

    public static NewsDetailFragment newInstance(){
        return new NewsDetailFragment();
    }

    public void initView(){
        news_title = view.findViewById(R.id.news_title);
        news_content = view.findViewById(R.id.news_content);
        news_source = view.findViewById(R.id.news_source);
        news_date = view.findViewById(R.id.news_date);
    }

    @Override
    public void onResume() {
        System.out.println("新闻详情页onResume");
        super.onResume();
    }

    @Override
    public NewsDetailPresenter createPresenter() {
        return new NewsDetailPresenter();
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }


    public void setId(String id){
        myPresenter.setNews(id);
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void setView(SingleNews news) {
        System.out.println("title = "+news.title);
        news_title.setText(news.title);
        news_content.setText(news.content);
        news_date.setText(news.time);
        news_source.setText(news.source);
    }
}
