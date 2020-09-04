package com.java.zhangzhexin.model;

import com.google.gson.JsonObject;

public class NewsCard {
    public String title;
    public String time;
    public String source; //for test
    public String id;
    public boolean visited = false;

    //for test
    public NewsCard(String title, String time, String source){
        this.time = time;
        this.title = title;
        this.source = source;
    }

    public NewsCard(JsonObject obj) {
        time = obj.get("date").toString();
        title = obj.get("title").toString();
        source = obj.get("source").toString();
//        System.out.println("string:"+obj.get("_id").getAsString());
        id = obj.get("_id").getAsString();
    }

}
