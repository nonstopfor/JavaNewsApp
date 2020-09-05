package com.java.zhangzhexin.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
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
import com.java.zhangzhexin.set.SetChannelActivity;

public class NewsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter adapter;
    private Tab tabObject;
    private ImageView editButton;
    private List<String>categories;

    private View view;

    public NewsFragment(Tab tabObject){
        this.tabObject = tabObject;
        this.categories = tabObject.getTabs();
        //this.categories = new ArrayList<>(Arrays.asList("news","paper","news","paper","news","paper","news","paper","news","paper","news","paper"));
        //System.out.println("categories = "+categories);
    }

    public static NewsFragment newInstance(Tab tabObject){
        NewsFragment newsFragment = new NewsFragment(tabObject);
        return newsFragment;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        adapter = new MyPagerAdapter(getChildFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        if(view == null){
            System.out.println("NewsFragment view为空");
            view = inflater.inflate(R.layout.fragment_news, container,false);
            initView();
            initSet();
        }
        else{
            System.out.println("NewsFragment view非空 不需要重新设置");
        }
        //view = inflater.inflate(R.layout.fragment_news, container,false);
        return view;
    }

    public void initView(){
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        editButton = view.findViewById(R.id.editButton);

    }

    public void initSet(){
        editButton.setOnClickListener(v -> {
            System.out.println("点击分类按钮，将弹出分类页面");
            Intent intent = new Intent(getContext(), SetChannelActivity.class);
            startActivity(intent);
            //TODO: 接收
        });
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) editButton.getLayoutParams();
//        System.out.println("tablayout height = "+tabLayout.getLayoutParams().height);
//        params.height = tabLayout.getLayoutParams().height;
//        System.out.println("height = "+ params.height);
//        editButton.setLayoutParams(params);
        adapter = new MyPagerAdapter(getChildFragmentManager(),FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(@NonNull FragmentManager fm,int behavior) {
            super(fm,behavior);
            System.out.println("");
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
            System.out.println("getItem: type = "+categories.get(position)+" keyword = "+"");
            return NewsListFragment.newInstance(categories.get(position),"");
        }

        @Override
        public int getCount() {
            return categories.size();
        }
    }


}

