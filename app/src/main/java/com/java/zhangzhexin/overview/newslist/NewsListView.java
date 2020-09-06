package com.java.zhangzhexin.overview.newslist;

import android.widget.TextView;

import com.java.zhangzhexin.BaseView;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.overview.MyListView;

import java.util.List;

public interface NewsListView extends MyListView<NewsCard> {

    void setColor(TextView title);

}
