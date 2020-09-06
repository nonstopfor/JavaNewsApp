package com.java.zhangzhexin.overview;

import com.java.zhangzhexin.BaseView;
import com.java.zhangzhexin.model.BaseCard;
import com.java.zhangzhexin.model.NewsCard;

import java.util.List;

public interface MyListView<MyCard extends BaseCard> extends BaseView {

    //重置newslist(初始化/refresh)

    void resetList(List<MyCard> data);

    //追加newslist
    void appendList(List<MyCard> data);

}
