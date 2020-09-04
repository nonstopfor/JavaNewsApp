package com.java.zhangzhexin.news.newslist;

import com.google.gson.JsonObject;
import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.BaseView;
import com.java.zhangzhexin.model.DataManager;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.model.UrlManager;

import java.util.ArrayList;
import java.util.List;

public class NewsListPresenter extends BasePresenter<NewsListView> {
    private int page = 0;
    private DataManager dataManager;

    public NewsListPresenter(String type,String keyword){
        super(type,keyword);
        System.out.println("presenter type = "+type+", keyword = "+keyword);
        dataManager = new DataManager(type);
    }

    public void getMoreNews(int size) throws InterruptedException {
        List<NewsCard> result = dataManager.getMoreNews(size);
//        List<NewsCard> result = new ArrayList<>();
//        result.add(new NewsCard("新闻5","2020-2-1","清新时报"));
//        result.add(new NewsCard("新闻6","2020-2-2","清新时报"));
//        result.add(new NewsCard("新闻7","2020-2-3","清新时报"));
//        result.add(new NewsCard("新闻8","2020-2-4","清新时报"));
//        result.add(new NewsCard("新闻5","2020-2-1","清新时报"));
//        result.add(new NewsCard("新闻6","2020-2-2","清新时报"));
//        result.add(new NewsCard("新闻7","2020-2-3","清新时报"));
//        result.add(new NewsCard("新闻8","2020-2-4","清新时报"));
//        result.add(new NewsCard("新闻5","2020-2-1","清新时报"));
//        result.add(new NewsCard("新闻6","2020-2-2","清新时报"));
//        result.add(new NewsCard("新闻7","2020-2-3","清新时报"));
//        result.add(new NewsCard("新闻8","2020-2-4","清新时报"));
//        result.add(new NewsCard("新闻5","2020-2-1","清新时报"));
//        result.add(new NewsCard("新闻6","2020-2-2","清新时报"));
//        result.add(new NewsCard("新闻7","2020-2-3","清新时报"));
//        result.add(new NewsCard("新闻8","2020-2-4","清新时报"));
//        result.add(new NewsCard("新闻5","2020-2-1","清新时报"));
//        result.add(new NewsCard("新闻6","2020-2-2","清新时报"));
//        result.add(new NewsCard("新闻7","2020-2-3","清新时报"));
//        result.add(new NewsCard("新闻8","2020-2-4","清新时报"));
//        System.out.println("result = "+result);
        assert(result!=null);
        myView.appendNewsList(result);
    }


    public void refreshNews(int size) throws InterruptedException {
        List<NewsCard> result = dataManager.RefreshNews(size);
//        List<NewsCard> result = new ArrayList<>();
//        result.add(new NewsCard("新闻1","2020-2-1","清新时报"));
//        result.add(new NewsCard("新闻2","2020-2-2","清新时报"));
//        result.add(new NewsCard("新闻3","2020-2-3","清新时报"));
//        result.add(new NewsCard("新闻4","2020-2-4","清新时报"));
//        result.add(new NewsCard("新闻1","2020-2-1","清新时报"));
//        result.add(new NewsCard("新闻2","2020-2-2","清新时报"));
//        result.add(new NewsCard("新闻3","2020-2-3","清新时报"));
//        result.add(new NewsCard("新闻4","2020-2-4","清新时报"));
//        result.add(new NewsCard("新闻1","2020-2-1","清新时报"));
//        result.add(new NewsCard("新闻2","2020-2-2","清新时报"));
//        result.add(new NewsCard("新闻3","2020-2-3","清新时报"));
//        result.add(new NewsCard("新闻4","2020-2-4","清新时报"));
//        result.add(new NewsCard("新闻1","2020-2-1","清新时报"));
//        result.add(new NewsCard("新闻2","2020-2-2","清新时报"));
//        result.add(new NewsCard("新闻3","2020-2-3","清新时报"));
//        result.add(new NewsCard("新闻4","2020-2-4","清新时报"));
//        result.add(new NewsCard("新闻1","2020-2-1","清新时报"));
//        result.add(new NewsCard("新闻2","2020-2-2","清新时报"));
//        result.add(new NewsCard("新闻3","2020-2-3","清新时报"));
//        result.add(new NewsCard("新闻4","2020-2-4","清新时报"));
//        System.out.println("result = "+result);
        assert(result!=null);
        myView.resetNewsList(result);
    }



}
