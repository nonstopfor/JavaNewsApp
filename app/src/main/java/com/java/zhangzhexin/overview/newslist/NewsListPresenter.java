package com.java.zhangzhexin.overview.newslist;

import android.content.Intent;


import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.model.NewsDataManager;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.detail.newsdetail.NewsDetailActivity;

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
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("news_id",news.id);
        myView.start(intent);
    }

    public void getMoreNews(int size) {
        List<NewsCard> result = null;
        try {
            result = newsDataManager.getMoreNews(size);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert(result!=null);
        myView.appendNewsList(result);
    }


    public void refreshNews(int size) {
        System.out.println("refreshNews!");
        List<NewsCard> result = null;
        try {
            result = newsDataManager.RefreshNews(size);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert(result!=null);
        myView.resetNewsList(result);
    }



}
