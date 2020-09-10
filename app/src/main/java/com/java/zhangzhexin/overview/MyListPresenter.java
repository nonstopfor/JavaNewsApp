package com.java.zhangzhexin.overview;

import android.view.View;

import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.model.BaseCard;
import com.java.zhangzhexin.model.BaseManager;

public abstract class MyListPresenter<MyManager extends BaseManager, MyCard extends BaseCard> extends BasePresenter<MyListView<MyCard>> {
    protected MyManager dataManager;
    protected String type;
    protected String keyword;


    public MyListPresenter(String type, String keyword){
        this.type = type;
        this.keyword = keyword;
        dataManager = createManager(type);
    }

    public abstract MyManager createManager(String type);

    public abstract void openDetail(View view, MyCard card);

    public abstract void getMoreData(int size);

    public abstract void refreshData(int size);



}
