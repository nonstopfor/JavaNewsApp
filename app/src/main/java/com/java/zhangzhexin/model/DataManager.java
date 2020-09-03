package com.java.zhangzhexin.model;

import com.java.zhangzhexin.BuildConfig;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    List<NewsCard> allNews;
    String type;
    int idx = 0;

    public DataManager(String type) {
        this.type = type;
    }

    public void refresh() throws InterruptedException {
        allNews = UrlManager.getAllNews(type);
        idx = 0;
    }

    public List<NewsCard> getMoreNews(int size) {
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

    public List<NewsCard> RefreshNews(int size) throws InterruptedException {
        refresh();
        return getMoreNews(size);
    }
}