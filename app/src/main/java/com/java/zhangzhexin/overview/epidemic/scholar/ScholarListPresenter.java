package com.java.zhangzhexin.overview.epidemic.scholar;

import android.content.Intent;
import android.view.View;

import com.java.zhangzhexin.detail.DetailActivity;
import com.java.zhangzhexin.model.EntityCard;
import com.java.zhangzhexin.model.EntityManager;
import com.java.zhangzhexin.model.ScholarCard;
import com.java.zhangzhexin.model.ScholarManager;
import com.java.zhangzhexin.overview.MyListPresenter;

import java.util.List;

public class ScholarListPresenter extends MyListPresenter<ScholarManager, ScholarCard> {

    @Override
    public ScholarManager createManager(String type) {
        return new ScholarManager();
    }

    @Override
    public void openDetail(View view, ScholarCard card) {
        Intent intent = new Intent(myView.getMyContext(), DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("type","scholar");
        intent.putExtra("scholar_id",card.idx);
        myView.start(intent);
    }


    public ScholarListPresenter(String type, String keyword){
        super(type, keyword);
    }



    @Override
    public void getMoreData(int size) {
        //不调用getMoreData,学者一次返回吧
        List<ScholarCard> result = null;
        result = dataManager.getMoreScholars(size);
        assert(result!=null);
        myView.appendList(result);
    }

    @Override
    public void refreshData(int size) {
        System.out.println("refreshScholar!");
        List<ScholarCard> result = null;
        try {
            result = dataManager.refresh(size);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert(result!=null);
        myView.resetList(result);
    }
}
