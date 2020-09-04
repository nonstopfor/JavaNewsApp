package com.java.zhangzhexin;

import android.app.Application;

import com.java.zhangzhexin.model.DaoMaster;
import com.java.zhangzhexin.model.DaoSession;

import org.greenrobot.greendao.*;
import org.greenrobot.greendao.database.Database;

public class App extends Application {

    private static DaoSession daoSession = null;

    @Override
    public void onCreate() {
        super.onCreate();
//        System.out.println("App start!");
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "news-db");
        Database db = helper.getWritableDb();

        daoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

}
