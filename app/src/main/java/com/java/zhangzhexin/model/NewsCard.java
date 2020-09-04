package com.java.zhangzhexin.model;

import com.google.gson.JsonObject;

public class NewsCard {
    public String title;
    public String time;
    public String source; //for test
    public long id;

    //for test
    public NewsCard(String title, String time, String source){
        this.time = time;
        this.title = title;
        this.source = source;
    }

    public NewsCard(JsonObject obj) {
        time = obj.get("time").toString();
        title = obj.get("title").toString();
//        source = obj.get("source").toString();
//        System.out.println("string:"+obj.get("_id").getAsString());
//        id = Long.valueOf(obj.get("_id").getAsString(),16);
    }

}
