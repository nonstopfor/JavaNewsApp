package com.java.zhangzhexin.model;

import android.content.SharedPreferences;

import com.java.zhangzhexin.App;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import org.greenrobot.greendao.converter.PropertyConverter;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Tab {

    public enum TabType {news,epidemicData,scholar,graph}

    private static Tab tab = null;

    public static Tab getInstance() {
        if (tab == null) {
            tab = new Tab();
        }
        return tab;
    }

    @Id
    public Long id = 0L;

    @Convert(columnType = String.class, converter = StringConverter.class)
    private List<String> tabs;

    public static List<String> allTabs = new ArrayList<>(Arrays.asList("news", "paper", "疫情数据", "知识图谱", "知疫学者"));

    public static TabType getType(String name){
        switch (name) {
            case "疫情数据":
                return TabType.epidemicData;
            case "知识图谱":
                return TabType.graph;
            case "知疫学者":
                return TabType.scholar;
            default:
                return TabType.news;
        }
    }

    private Tab() {
        load();
    }

    private boolean inDataBase() {
        DaoSession daoSession = App.getDaoSession();
        TabDao tabDao = daoSession.getTabDao();
        if (tabDao.queryBuilder().where(TabDao.Properties.Id.eq(id)).list().isEmpty()) return false;
        return true;
    }

    private void save() {
        DaoSession daoSession = App.getDaoSession();
        TabDao tabDao = daoSession.getTabDao();
        if (inDataBase()) {
            tabDao.update(this);
        } else {
            tabDao.insert(this);
        }
    }

    private void load() {
        if (!inDataBase()) {
            tabs = new ArrayList<>();
            for (String tab : allTabs) {
                addTab(tab);
            }
            return;
        }
        DaoSession daoSession = App.getDaoSession();
        TabDao tabDao = daoSession.getTabDao();
        this.tabs = tabDao.load(id).tabs;
    }

    @Generated(hash = 486278211)
    public Tab(Long id, List<String> tabs) {
        this.id = id;
        this.tabs = tabs;
    }

    public boolean addTab(String name) {
        if (tabs.contains(name)) return false;
        tabs.add(name);
        save();
        return true;
    }

    public boolean deleteTab(String name) {
        if (!tabs.contains(name)) return false;
        tabs.remove(name);
        save();
        return true;
    }

    public List<String> getTabs() {
        return tabs;
    }

    public List<String> getComplementTabs() {
        List<String> result = new ArrayList<>();
        for (String t : allTabs) {
            if (!tabs.contains(t)) result.add(t);
        }
        return result;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTabs(List<String> tabs) {
        this.tabs = tabs;
        save();
    }
}
