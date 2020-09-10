package com.java.zhangzhexin.detail.entitydetail;

import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.model.EntityManager;
import com.java.zhangzhexin.model.NewsDataManager;


/*
详情页和model的交互：
1. 请求正文
2. 标记已读（数据库）
 */
public class EntityDetailPresenter extends BasePresenter<EntityDetailView> {
    public EntityDetailPresenter(){}

    public void setEntity(int entity_id)  {
        myView.setView(EntityManager.getEntity(entity_id));
    }

}
