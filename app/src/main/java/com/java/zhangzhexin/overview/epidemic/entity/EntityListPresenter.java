package com.java.zhangzhexin.overview.epidemic.entity;

import android.content.Intent;
import android.view.View;

import com.java.zhangzhexin.detail.DetailActivity;
import com.java.zhangzhexin.model.EntityCard;
import com.java.zhangzhexin.model.EntityManager;
import com.java.zhangzhexin.overview.MyListPresenter;

import java.util.List;

public class EntityListPresenter extends MyListPresenter<EntityManager, EntityCard> {

    @Override
    public EntityManager createManager(String type) {
        return new EntityManager();
    }

    @Override
    public void openDetail(View view, EntityCard card) {
        Intent intent = new Intent(myView.getMyContext(), DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("type","entity");
        intent.putExtra("entity_id",card.idx);
        //TODO：把card的id放进来
        myView.start(intent);
        //System.out.println("EntityList的点击事件尚未实现");
        //TODO：不准备
    }


    public EntityListPresenter(String type, String keyword){
        super(type, keyword);
//        this.type = type;
//        this.keyword = keyword;
//        //System.out.println("presenter type = "+type+", keyword = "+keyword);
//        newsDataManager = new NewsDataManager(type);
    }



    @Override
    public void getMoreData(int size) {
        //不调用getMoreData,实体仅搜索时调用
////        List<EntityCard> result = null;
////        try {
////            result = dataManager.getMoreNews(size);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        List<EntityCard> result = new ArrayList<>();
////        result.add(new EntityCard("label1"));
////        result.add(new EntityCard("label2"));
//        assert(result!=null);
//        myView.appendList(result);
    }

    @Override
    public void refreshData(int size) {
        System.out.println("refreshNews!");
        List<EntityCard> result = null;
        try {
            result = dataManager.getEntityCardList(keyword);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        List<EntityCard> result = new ArrayList<>();
//        result.add(new EntityCard("label4"));
//        result.add(new EntityCard("label5"));
        assert(result!=null);
        myView.resetList(result);
    }
}
