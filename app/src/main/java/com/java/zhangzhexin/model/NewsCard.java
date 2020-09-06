package com.java.zhangzhexin.model;

import com.google.gson.JsonObject;
import com.java.zhangzhexin.App;

public class NewsCard extends BaseCard{
    public String title;
    public String time;
    public String source; //for test
    public String id;
//    public boolean visited = false;

    //for test
    public NewsCard(String title, String time, String source) {
        this.time = time;
        this.title = title;
        this.source = source;
    }

    public NewsCard(JsonObject obj) {
        time = obj.get("date").getAsString();
        title = obj.get("title").getAsString();
        source = obj.get("source").getAsString();
        if (source.length() == 0) source = "来源未知";
//        System.out.println("string:"+obj.get("_id").getAsString());
        id = obj.get("_id").getAsString();
    }

    public NewsCard(SingleNews singleNews) {
        id = singleNews.id;
        time = singleNews.time;
        source = singleNews.source;
        title = singleNews.title;
    }

    public boolean visited(){
        DaoSession daoSession = App.getDaoSession();
        SingleNewsDao singleNewsDao = daoSession.getSingleNewsDao();
        if (singleNewsDao.queryBuilder().where(SingleNewsDao.Properties.Id.eq(id)).list().isEmpty()) return false;
        return true;
    }
}
