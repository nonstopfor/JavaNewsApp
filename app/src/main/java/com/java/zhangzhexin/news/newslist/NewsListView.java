package com.java.zhangzhexin.news.newslist;

import com.java.zhangzhexin.BaseView;
import com.java.zhangzhexin.model.NewsCard;

import java.util.List;

public interface NewsListView extends BaseView {

    //重置newslist(初始化/refresh)
    void resetNewsList(int size) throws InterruptedException;

    //追加newslist
    void appendNewsList(int size);

}
