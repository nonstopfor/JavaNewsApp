package com.java.zhangzhexin.news.newsdetail;

import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.model.NewsDataManager;


/*
详情页和model的交互：
1. 请求正文
2. 标记已读（数据库）
 */
public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {
    private NewsDataManager newsDataManager;
    public NewsDetailPresenter(){
        newsDataManager = new NewsDataManager();
    }

    public void setNews(String news_id)  {
        try {
            myView.setNews(newsDataManager.getContent(news_id));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
