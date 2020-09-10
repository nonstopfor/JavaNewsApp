package com.java.zhangzhexin.detail.newsdetail;

import android.content.Intent;

import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.MainActivity;
import com.java.zhangzhexin.model.NewsDataManager;

public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {
    private NewsDataManager newsDataManager;
    public NewsDetailPresenter(){
        newsDataManager = new NewsDataManager();
    }

    public void setNews(String news_id)  {
        try {
            myView.setView(newsDataManager.getContent(news_id));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
