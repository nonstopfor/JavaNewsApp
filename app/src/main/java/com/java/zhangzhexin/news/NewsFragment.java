package com.java.zhangzhexin.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.java.zhangzhexin.model.Tab;
import com.java.zhangzhexin.news.newslist.NewsListFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.java.zhangzhexin.R;

public class NewsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter adapter;
    private Tab tabObject;
    private List<String>categories;
    //TODO: 分类的增加/删除
    public NewsFragment(Tab tabObject){
        this.tabObject = tabObject;
        this.categories = tabObject.tabs;
        //System.out.println("categories = "+categories);
    }

    public static NewsFragment newInstance(Tab tabObject){
        NewsFragment newsFragment = new NewsFragment(tabObject);
        return newsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new MyPagerAdapter(getChildFragmentManager());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news, container,false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

//        for(String category : categories){
//            tabLayout.addTab(tabLayout.newTab().setText(category));
//        }
        return view;
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter{

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);

        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //System.out.println("category = "+categories.get(position));
            return categories.get(position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //System.out.println("getItem: type = "+categories.get(position)+" keyword = "+"");
            return NewsListFragment.newInstance(categories.get(position),"");
            //TODO: 传搜索的关键字
        }

        @Override
        public int getCount() {
            return categories.size();
        }
    }


}

