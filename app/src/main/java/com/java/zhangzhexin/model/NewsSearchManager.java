package com.java.zhangzhexin.model;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class NewsSearchManager {
    public static List<NewsCard> preDownloadNewsList;

    public static List<NewsCard> searchNews(String key) throws InterruptedException {
        while (preDownloadNewsList == null) {
            sleep(500);
        }
        List<NewsCard> result = new ArrayList<>();
        for (NewsCard card : preDownloadNewsList) {
            if (card.title.contains(key)) {
                result.add(card);
            }
        }
        return result;
    }
}
