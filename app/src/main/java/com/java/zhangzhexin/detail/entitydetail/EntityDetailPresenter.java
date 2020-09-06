package com.java.zhangzhexin.detail.entitydetail;

import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.model.NewsDataManager;


/*
详情页和model的交互：
1. 请求正文
2. 标记已读（数据库）
 */
public class EntityDetailPresenter extends BasePresenter<EntityDetailView> {
    private NewsDataManager newsDataManager;
    public EntityDetailPresenter(){
        newsDataManager = new NewsDataManager();
    }

    public void setNews(String news_id)  {
        try {
            myView.setView(newsDataManager.getContent(news_id));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
