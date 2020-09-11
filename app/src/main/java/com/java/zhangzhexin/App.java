package com.java.zhangzhexin;

import android.app.Application;

import com.java.zhangzhexin.model.DaoMaster;
import com.java.zhangzhexin.model.DaoSession;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.model.NewsDataManager;
import com.java.zhangzhexin.model.NewsSearchManager;
import com.java.zhangzhexin.model.ScholarManager;
import com.java.zhangzhexin.model.UrlManager;

import org.greenrobot.greendao.database.Database;

import java.util.List;

public class App extends Application {

    private static DaoSession daoSession = null;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("App start!");
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "news-db");
        Database db = helper.getWritableDb();

        daoSession = new DaoMaster(db).newSession();
        NewsDataManager.assetManager = getAssets();

        new Thread() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                int siz = 500;

                for (int page = 1; ; ++page) {

                    List<NewsCard> cards = null;
                    try {
                        cards = UrlManager.getNewsList("news", page, siz);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                        NewsSearchManager.preDownloadNewsList = UrlManager.getNewsList("news", 1, 1000);
                    NewsSearchManager.preDownloadNewsList.addAll(cards);
                    System.out.println("download page:" + page);
//                        System.out.println("cards.size:" + cards.size());
                    if (cards.isEmpty()) {
                        if (page > 100) break;
                        else --page;
                    }

                }
                long endTime = System.currentTimeMillis();

                System.out.println("finish thread run, took " + (endTime - startTime) + " ms");
            }
        }.start();

    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
