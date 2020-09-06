package com.java.zhangzhexin.detail.newsdetail;

import com.java.zhangzhexin.BaseView;
import com.java.zhangzhexin.model.SingleNews;

public interface NewsDetailView extends BaseView {

    //TODO： 新闻/图谱/学者 实现自己的setData函数
    void setNews(SingleNews news);
}
