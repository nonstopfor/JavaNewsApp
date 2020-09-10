package com.java.zhangzhexin.model;

import android.content.res.AssetManager;

import com.java.zhangzhexin.App;
import com.java.zhangzhexin.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class NewsDataManager extends BaseManager {

    public static AssetManager assetManager;

    List<NewsCard> allNews;
    String type;
    int idx = 0;
    int page = 0;

    public NewsDataManager() {
    }

    public NewsDataManager(String type) {
        this.type = type;
        allNews = new ArrayList<>();
    }

    public void refreshOld() throws InterruptedException {
        allNews = UrlManager.getAllNews(type);
        idx = 0;
    }

    public void refreshNew() {
        if (!isEvent(type)) {
            page = 0;
            if (!allNews.isEmpty()) {
                allNews.clear();
            }
        } else {
            try {
                InputStream is = assetManager.open(type + ".txt");
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                idx = 0;
                if (!allNews.isEmpty()) {
                    allNews.clear();
                }
                String str = "";
                while ((str = br.readLine()) != null) {
                    String[] strs = str.split("###");
                    String title = strs[0].substring(1);
                    String id = strs[1].substring(1);
                    String time = strs[2].substring(1);
                    String source = strs[3].substring(1);
                    allNews.add(new NewsCard(title, time, source, id));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    boolean isEvent(String type) {
        if (type.equals("history") || type.equals("news") || type.equals("paper")) {
            return false;
        }
        return true;
    }

    public void refreshHistory() {
        idx = 0;
        DaoSession daoSession = App.getDaoSession();
        SingleNewsDao singleNewsDao = daoSession.getSingleNewsDao();
        allNews.clear();
        List<SingleNews> allSingleNews = singleNewsDao.loadAll();

        for (int i = allSingleNews.size() - 1; i >= 0; --i) {
            allNews.add(new NewsCard(allSingleNews.get(i)));
        }

    }

    public List<NewsCard> getMoreNewsOld(int size) {
        int len = allNews.size();
//        if (BuildConfig.DEBUG && !(len > 0)) {
//            throw new AssertionError("Assertion failed");
//        }
        int to = Math.min(len, idx + size);
        List<NewsCard> result = new ArrayList<>();
        for (int i = idx; i < to; ++i) {
            result.add(allNews.get(i));
        }
        idx = to;
        return result;
    }

    public List<NewsCard> getMoreNewsNew(int size) throws InterruptedException {
        if (isEvent(type)) return getMoreNewsOld(size);
        ++page;
        List<NewsCard> r = UrlManager.getNewsList(type, page, size);
        allNews.addAll(r);
        return r;
    }

    public List<NewsCard> getMoreNews(int size) throws InterruptedException {
//        return getMoreNewsOld(size);
        if (type.equals("history")) {
            return getMoreNewsOld(size);
        }
        return getMoreNewsNew(size);
    }

    public List<NewsCard> RefreshNews(int size) throws InterruptedException {
//        refreshOld();
        if (type.equals("history")) {
            refreshHistory();
        } else {
            refreshNew();
        }
        return getMoreNews(size);
    }

    public SingleNews tryGetSingleNews(String id) {
        DaoSession daoSession = App.getDaoSession();
        SingleNewsDao singleNewsDao = daoSession.getSingleNewsDao();
        List<SingleNews> singleNews = singleNewsDao.queryBuilder().where(SingleNewsDao.Properties.Id.eq(id)).list();
        if (singleNews.isEmpty()) return null;
        return singleNews.get(0);
    }

    public SingleNews getContent(String id) throws InterruptedException {
        SingleNews news = tryGetSingleNews(id);
        if (news != null) return news;
        SingleNews singleNews = UrlManager.getSingleNewsById(id);
        singleNews.save();
        return singleNews;
    }
}