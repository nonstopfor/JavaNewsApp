package com.java.zhangzhexin.overview.newslist;

import android.content.Intent;


import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.model.BaseCard;
import com.java.zhangzhexin.model.BaseManager;
import com.java.zhangzhexin.model.NewsDataManager;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.detail.newsdetail.NewsDetailActivity;
import com.java.zhangzhexin.overview.MyListPresenter;

import java.util.List;

public class NewsListPresenter extends MyListPresenter<NewsDataManager,NewsCard> {
    private int page = 0;
    //private NewsDataManager newsDataManager;
    private String type;
    private String keyword;


    @Override
    public NewsDataManager createMangaer(String type) {
        return new NewsDataManager(type);
    }

    public NewsListPresenter(String type, String keyword){
        super(type, keyword);
//        this.type = type;
//        this.keyword = keyword;
//        //System.out.println("presenter type = "+type+", keyword = "+keyword);
//        newsDataManager = new NewsDataManager(type);
    }

    @Override
    public void openDetail(NewsCard card) {
        Intent intent = new Intent(myView.getMyContext(), NewsDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("news_id",card.id);
        myView.start(intent);
    }

    @Override
    public void getMoreData(int size) {
        List<NewsCard> result = null;
        try {
            result = dataManager.getMoreNews(size);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert(result!=null);
        myView.appendList(result);
    }

    @Override
    public void refreshData(int size) {
        System.out.println("refreshNews!");
        List<NewsCard> result = null;
        try {
            result = dataManager.RefreshNews(size);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert(result!=null);
        myView.resetList(result);
    }


}
