package com.java.zhangzhexin.detail.scholardetail;

import com.java.zhangzhexin.BaseView;
import com.java.zhangzhexin.model.EntityCard;

public interface ScholarDetailView extends BaseView {

    //TODO： 新闻/图谱/学者 实现自己的setData函数
    void setView(EntityCard news);
}
