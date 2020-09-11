package com.java.zhangzhexin.overview.newslist;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;


import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.NewsDataManager;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.detail.DetailActivity;
import com.java.zhangzhexin.model.NewsSearchManager;
import com.java.zhangzhexin.overview.MyListPresenter;

import java.util.List;

public class NewsListPresenter extends MyListPresenter<NewsDataManager,NewsCard> {

    @Override
    public NewsDataManager createManager(String type) {
        return new NewsDataManager(type);
    }

    public NewsListPresenter(String type, String keyword){
        super(type, keyword);
    }

    @Override
    public void openDetail(View view, NewsCard card) {
        //TextView title = view.findViewById(R.id.news_title);
        //((NewsListView)myView).setColor(title);
        Intent intent = new Intent(myView.getMyContext(), DetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("type","news");
        intent.putExtra("news_id",card.id);
        myView.start(intent);
    }

    @Override
    public void getMoreData(int size) {
        if(keyword.equals("")) {
            List<NewsCard> result = null;
            try {
                result = dataManager.getMoreNews(size);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assert (result != null);
//            System.out.println("新得到的数据是" + result);
            myView.appendList(result);
        }
    }

    @Override
    public void refreshData(int size) {
//        System.out.println("refreshNews!");
        List<NewsCard> result = null;
        try {
            if(keyword.equals(""))
                result = dataManager.RefreshNews(size);
            else
                result = NewsSearchManager.searchNews(keyword);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert(result!=null);
        myView.resetList(result);
    }
}
