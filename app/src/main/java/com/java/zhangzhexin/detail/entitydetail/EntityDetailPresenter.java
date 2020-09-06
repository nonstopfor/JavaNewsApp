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
    private EntityManager entityManager;
    public EntityDetailPresenter(){
        entityManager = new EntityManager();
    }

    public void setEntity(int entity_id)  {
        myView.setView(entityManager.getEntity(entity_id));
    }

//    public void back(){
//        Intent intent = new Intent(myView.getMyContext(),MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        myView.start(intent);
////            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
////            startActivity(intent);
//    }
}
