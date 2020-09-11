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
        myView.start(intent);
    }


    public EntityListPresenter(String type, String keyword){
        super(type, keyword);
    }



    @Override
    public void getMoreData(int size) {
    }

    @Override
    public void refreshData(int size) {
//        System.out.println("refreshNews!");
        List<EntityCard> result = null;
        try {
            result = dataManager.getEntityCardList(keyword);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert(result!=null);
        myView.resetList(result);
    }
}
