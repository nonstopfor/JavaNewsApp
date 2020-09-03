package com.java.zhangzhexin.model;

import com.google.gson.*;

public class SingleNews {
    public String content;
    public String date;
    public String category;
    public String title;
    public String source;
    public int id;

    public SingleNews(JsonObject obj) {
        content = obj.get("content").toString();
        date = obj.get("date").toString();
        category = obj.get("category").toString();
        title = obj.get("title").toString();
        source = obj.get("source").toString();
        id = obj.get("_id").getAsInt();

    }
}
