package com.java.zhangzhexin.news.newslist;

import com.java.zhangzhexin.BaseView;
import com.java.zhangzhexin.model.NewsCard;

import java.util.List;

public interface NewsListView extends BaseView {

    //重置newslist(初始化/refresh)

    void resetNewsList(List<NewsCard>data);

    //追加newslist
    void appendNewsList(List<NewsCard>data);

}
