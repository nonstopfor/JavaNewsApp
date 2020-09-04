package com.java.zhangzhexin.model;

import android.content.SharedPreferences;

import com.java.zhangzhexin.App;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;

import java.util.Arrays;
import java.util.List;

import org.greenrobot.greendao.converter.PropertyConverter;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Tab {

    @Id
    public Long id = 0L;

    @Convert(columnType = String.class, converter = StringConverter.class)
    public List<String> tabs;

    public Tab() {
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
        if (!inDataBase()) return;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTabs(List<String> tabs) {
        this.tabs = tabs;
    }
}
