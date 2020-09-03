package com.java.zhangzhexin.model;

import com.google.gson.*;

public class SingleNews {
    public String content;
    public String date;
    public String type;
    public String title;
    public String source;
    public long id;

    public SingleNews(JsonObject obj) {
        content = obj.get("content").toString();
        date = obj.get("date").toString();
        type = obj.get("type").toString();
        title = obj.get("title").toString();
        source = obj.get("source").toString();
        id = obj.get("_id").getAsLong();

    }
}
