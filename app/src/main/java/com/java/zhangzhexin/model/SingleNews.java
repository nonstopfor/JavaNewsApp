package com.java.zhangzhexin.model;

import com.google.gson.JsonObject;
import com.java.zhangzhexin.App;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

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


    @Generated(hash = 1061401129)
    public SingleNews(Long databaseId, String content, String time, String type,
                      String title, String source, @NotNull String id) {
        this.databaseId = databaseId;
        this.content = content;
        this.time = time;
        this.type = type;
        this.title = title;
        this.source = source;
        this.id = id;
    }

    public void save() {
        DaoSession daoSession = App.getDaoSession();
        SingleNewsDao singleNewsDao = daoSession.getSingleNewsDao();
        // TODO: 检查如果this已在数据库中，再调insert是否会有问题？ 会！
        if (singleNewsDao.queryBuilder().where(SingleNewsDao.Properties.Id.eq(id)).list().isEmpty()) {
//            System.out.println("Saving SingleNews");
            singleNewsDao.insert(this);
        }
        else{
//            System.out.println("SingleNews has already existed");
        }
//        System.out.println("SingleNews saved!");
    }

    @Generated(hash = 1516530826)
    public SingleNews() {
    }


    public Long getDatabaseId() {
        return this.databaseId;
    }


    public void setDatabaseId(Long databaseId) {
        this.databaseId = databaseId;
    }


    public String getContent() {
        return this.content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public String getTime() {
        return this.time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getType() {
        return this.type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getSource() {
        return this.source;
    }


    public void setSource(String source) {
        this.source = source;
    }


    public String getId() {
        return this.id;
    }


    public void setId(String id) {
        this.id = id;
    }


}
