package com.java.zhangzhexin.overview;

import android.content.Intent;

import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.detail.newsdetail.NewsDetailActivity;
import com.java.zhangzhexin.model.BaseCard;
import com.java.zhangzhexin.model.BaseManager;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.model.NewsDataManager;
import com.java.zhangzhexin.overview.newslist.NewsListView;

import java.util.List;

public abstract class MyListPresenter<MyManager extends BaseManager, MyCard extends BaseCard> extends BasePresenter<MyListView<MyCard>> {
    private int page = 0;
    protected MyManager dataManager;
    private String type;
    private String keyword;

    public abstract MyManager createMangaer(String type);
    public MyListPresenter(String type, String keyword){
        this.type = type;
        this.keyword = keyword;
        //System.out.println("presenter type = "+type+", keyword = "+keyword);
        dataManager = createMangaer(type);
    }

    public abstract void openDetail(MyCard card);

    public abstract void getMoreData(int size);

    public abstract void refreshData(int size);



}
