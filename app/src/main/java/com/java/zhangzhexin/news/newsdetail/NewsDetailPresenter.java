package com.java.zhangzhexin.news.newsdetail;

import com.java.zhangzhexin.BasePresenter;


/*
详情页和model的交互：
1. 请求正文
2. 标记已读（数据库）
 */
public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {

    public NewsDetailPresenter(){}

    public void setNews(String news_id){
        myView.setNews(null);
        //TODO:调用接口
    }
}
