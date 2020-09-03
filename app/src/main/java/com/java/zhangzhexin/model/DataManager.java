package com.java.zhangzhexin.model;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    List<NewsCard> allNews;
    String type;
    int idx = 0;

    public DataManager(String type) {
        this.type = type;
    }

    public void refresh() {
        allNews = UrlManager.getAllNews(type);
        idx = 0;
    }

    public List<NewsCard> getNews(int size) {
        System.out.println("size = "+size);
        int len = allNews.size();
        int to = Math.min(len, idx + size);
        List<NewsCard> result = new ArrayList<>();
        for (int i = idx; i < to; ++i) {
            result.add(allNews.get(i));
        }
        idx = to;
        System.out.println("result = "+result);
        return result;
    }

}
