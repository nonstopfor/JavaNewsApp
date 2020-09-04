package com.java.zhangzhexin.model;

import com.google.gson.*;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class SingleNews {

    @Id(autoincrement = true)
    public Long databaseId; // no actual use

    public String content;
    public String time;
    public String type;
    public String title;
    public String source;

    @NotNull
    @Index(unique = true)
    public String id; // real id



    public SingleNews(JsonObject obj) {
        content = obj.get("content").getAsString();
        time = obj.get("date").getAsString();
        type = obj.get("type").getAsString();
        title = obj.get("title").getAsString();
        source = obj.get("source").getAsString();
        id = obj.get("_id").getAsString();
    }


}
