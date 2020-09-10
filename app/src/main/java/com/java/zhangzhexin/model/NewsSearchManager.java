package com.java.zhangzhexin.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class NewsSearchManager {
    public static List<NewsCard> preDownloadNewsList = new ArrayList<>();

    public static List<NewsCard> searchNews(String key) throws InterruptedException {
        while (preDownloadNewsList == null) {
            sleep(500);
        }
        List<NewsCard> result = new ArrayList<>();
        int len = preDownloadNewsList.size();
        for (int i = 0; i < len; ++i) {
            NewsCard card = preDownloadNewsList.get(i);
            if (card.title.contains(key)) {
                result.add(card);
            }
        }
        return result;
    }
}
