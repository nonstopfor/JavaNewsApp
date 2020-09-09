package com.java.zhangzhexin.overview.epidemic.scholar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.java.zhangzhexin.model.EntityCard;
import com.java.zhangzhexin.model.EntityManager;
import com.java.zhangzhexin.model.ScholarCard;
import com.java.zhangzhexin.model.ScholarManager;
import com.java.zhangzhexin.overview.MyListFragment;

public class ScholarListFragment extends MyListFragment<ScholarAdapter.ViewHolder, ScholarAdapter, ScholarCard, ScholarManager, ScholarListView, ScholarListPresenter> implements ScholarListView {

    public ScholarListFragment(){}

    public static ScholarListFragment newInstance(String type, String keyword) {
        Bundle args = new Bundle();
        ScholarListFragment fragment = new ScholarListFragment();
        args.putString("type",type);
        args.putString("keyword",keyword);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ScholarAdapter createAdapter() {
        return new ScholarAdapter(getContext(),type);
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
    public ScholarListPresenter createPresenter() {
        return new ScholarListPresenter(type,keyword);
    }
}
