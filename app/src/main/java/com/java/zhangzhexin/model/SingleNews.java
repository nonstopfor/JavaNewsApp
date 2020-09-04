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
        content = obj.get("content").getAsString();
        date = obj.get("date").getAsString();
        type = obj.get("type").getAsString();
        title = obj.get("title").getAsString();
        source = obj.get("source").getAsString();
//        id = obj.get("_id").getAsLong();

    }
}
