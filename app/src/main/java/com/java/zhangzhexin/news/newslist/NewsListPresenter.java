package com.java.zhangzhexin.news.newslist;

import android.content.Intent;


import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.model.NewsDataManager;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.news.newsdetail.NewsDetailActivity;

import java.util.List;

public class NewsListPresenter extends BasePresenter<NewsListView> {
    private int page = 0;
    private NewsDataManager newsDataManager;
    private String type;
    private String keyword;
    public NewsListPresenter(String type,String keyword){
        this.type = type;
        this.keyword = keyword;
        //System.out.println("presenter type = "+type+", keyword = "+keyword);
        newsDataManager = new NewsDataManager(type);
    }

    public void openNewsDetail(NewsCard news){
        //TODO:切换时加特效
        Intent intent = new Intent(myView.getMyContext(), NewsDetailActivity.class);
        intent.putExtra("news_id",news.id);
        myView.start(intent);
    }

    public void getMoreNews(int size) throws InterruptedException {
        List<NewsCard> result = newsDataManager.getMoreNews(size);
        assert(result!=null);
        myView.appendNewsList(result);
    }


    public void refreshNews(int size) throws InterruptedException {
        System.out.println("refreshNews!");
        List<NewsCard> result = newsDataManager.RefreshNews(size);
        assert(result!=null);
        myView.resetNewsList(result);
    }



}
