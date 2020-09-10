package com.java.zhangzhexin.detail.scholardetail;

import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.model.EntityManager;
import com.java.zhangzhexin.model.ScholarManager;


/*
详情页和model的交互：
1. 请求正文
2. 标记已读（数据库）
 */
public class ScholarDetailPresenter extends BasePresenter<ScholarDetailView> {
    public ScholarDetailPresenter(){}

    public void setScholar(int scholar_id) {
        myView.setView(ScholarManager.getScholar(scholar_id));
//        myView.setView(EntityManager.getEntity(entity_id));

//    public void back(){
//        Intent intent = new Intent(myView.getMyContext(),MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//        myView.start(intent);
////            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
////            startActivity(intent);
//    }
    }
}
