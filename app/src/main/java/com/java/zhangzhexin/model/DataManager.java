package com.java.zhangzhexin.model;

import com.java.zhangzhexin.BuildConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class DataManager {

    List<NewsCard> allNews;
    String type;
    int idx = 0;
    int page = 0;

    public DataManager(){}

    public DataManager(String type) {
        this.type = type;
        allNews = new ArrayList<>();
    }

    public void refreshOld() throws InterruptedException {
        allNews = UrlManager.getAllNews(type);
        idx = 0;
    }

    public void refreshNew() {
        page = 0;
        if (!allNews.isEmpty()) {
            allNews.clear();
        }
    }

    public List<NewsCard> getMoreNewsOld(int size) {
        int len = allNews.size();
        if (BuildConfig.DEBUG && !(len > 0)) {
            throw new AssertionError("Assertion failed");
        }
        int to = Math.min(len, idx + size);
        List<NewsCard> result = new ArrayList<>();
        for (int i = idx; i < to; ++i) {
            result.add(allNews.get(i));
        }
        idx = to;
        return result;
    }

    public List<NewsCard> getMoreNewsNew(int size) throws InterruptedException {
        ++page;
        List<NewsCard> r = UrlManager.getNewsList(type, page, size);
        allNews.addAll(r);
        return r;
    }

    public List<NewsCard> getMoreNews(int size) throws InterruptedException {
//        return getMoreNewsOld(size);
        return getMoreNewsNew(size);
    }

    public List<NewsCard> RefreshNews(int size) throws InterruptedException {
//        refreshOld();
        refreshNew();
        return getMoreNews(size);
    }

    public SingleNews getContent(String id) throws InterruptedException {
        SingleNews singleNews = UrlManager.getSingleNewsById(id);
        singleNews.save();
        return singleNews;
    }
}