package com.java.zhangzhexin.model;

import java.util.ArrayList;
import java.util.List;

public class NewsSearchManager {
    public static List<NewsCard> preDownloadNewsList;

    public static List<NewsCard> searchNews(String key) {
        List<NewsCard> result = new ArrayList<>();
        for (NewsCard card : preDownloadNewsList) {
            if (card.title.contains(key)) {
                result.add(card);
            }
        }
        return result;
    }
}
