package com.java.zhangzhexin.news.newsdetail;

import com.java.zhangzhexin.BasePresenter;


/*
详情页和model的交互：
1. 请求正文
2. 标记已读（数据库）
 */
public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {

    public NewsDetailPresenter(String type, String keyword) {
        super(type, keyword);
    }
}
