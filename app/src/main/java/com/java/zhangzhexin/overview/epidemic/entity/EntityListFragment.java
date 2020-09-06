package com.java.zhangzhexin.overview.epidemic.entity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.java.zhangzhexin.model.EntityCard;
import com.java.zhangzhexin.model.EntityManager;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.model.NewsDataManager;
import com.java.zhangzhexin.overview.MyListFragment;
import com.java.zhangzhexin.overview.MyListPresenter;

import java.util.List;

public class EntityListFragment extends MyListFragment<EntityAdapter.ViewHolder, EntityAdapter, EntityCard, EntityManager, EntityListView, EntityListPresenter> implements EntityListView {

    public EntityListFragment(){}

    public static EntityListFragment newInstance(String type, String keyword) {
        Bundle args = new Bundle();
        EntityListFragment fragment = new EntityListFragment();
        args.putString("type",type);
        args.putString("keyword",keyword);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public EntityAdapter createAdapter() {
        return new EntityAdapter(getContext(),type);
    }


    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }


    @Override
    public EntityListPresenter createPresenter() {
        return new EntityListPresenter(type,keyword);
    }
}
