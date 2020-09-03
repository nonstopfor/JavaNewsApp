package com.java.zhangzhexin.model;

import com.google.gson.JsonObject;

public class NewsCard {
    public String title;
    public String date;
    public String source;
    public int id;

    public NewsCard(JsonObject obj) {
        date = obj.get("date").toString();
        title = obj.get("title").toString();
        source = obj.get("source").toString();
        id = obj.get("_id").getAsInt();
    }

}
