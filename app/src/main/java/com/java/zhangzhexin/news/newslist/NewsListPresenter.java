package com.java.zhangzhexin.news.newslist;

import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.BaseView;
import com.java.zhangzhexin.model.DataManager;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.model.UrlManager;

import java.util.List;

public class NewsListPresenter extends BasePresenter<NewsListView> {
    private int page = 0;
    private DataManager dataManager;

    public NewsListPresenter(String type,String keyword){
        super(type,keyword);
        System.out.println("presenter type = "+type+", keyword = "+keyword);
        dataManager = new DataManager(type);
    }

    public void getMoreNews(int size){
        List<NewsCard> result = dataManager.getMoreNews(size);
        assert(result!=null);
        myView.appendNewsList(result);
    }

    public void refreshNews(int size){
        List<NewsCard> result = dataManager.RefreshNews(size);
        assert(result!=null);
        myView.resetNewsList(result);
    }



}
