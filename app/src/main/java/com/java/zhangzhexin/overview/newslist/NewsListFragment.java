package com.java.zhangzhexin.overview.newslist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.model.NewsDataManager;
import com.java.zhangzhexin.overview.MyListFragment;

public class NewsListFragment extends MyListFragment<NewsAdapter.ViewHolder,NewsAdapter,NewsCard, NewsDataManager,NewsListView,NewsListPresenter> implements NewsListView{

    public NewsListFragment(){}

    public static NewsListFragment newInstance(String type, String keyword) {
        //System.out.println("newInstance NewsListFragment, type = "+type+" keyword = "+keyword);
        Bundle args = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        args.putString("type",type);
        args.putString("keyword",keyword);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public NewsAdapter createAdapter() {
        return new NewsAdapter(getContext(),type);
    }

    @Override
    public NewsListPresenter createPresenter() {
        //System.out.println("in createPresenter, type = "+type+", keyword = "+keyword);
        return new NewsListPresenter(type,keyword);
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }

    @Override
    public void setColor(TextView title) {
        if(!type.equals("history"))
            title.setTextColor(getResources().getColor(R.color.colorReadNews)); //改变颜色
    }
}
