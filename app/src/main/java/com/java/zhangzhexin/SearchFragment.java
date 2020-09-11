package com.java.zhangzhexin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import com.java.zhangzhexin.HomeFragment.MyPagerAdapter;

import java.util.Arrays;
import java.util.List;

public class SearchFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter adapter;
    private String keyword;
    private boolean visible = false;

    private View view;
    private List<String> categories = Arrays.asList("news","知识图谱");

    public static SearchFragment newInstance(){
        return new SearchFragment();
    }

    public void setKeyword(String keyword){
        this.keyword = keyword;
        //FIXME: 重复搜索 子fragment没有刷新 不能通过
        if(visible) {
//            System.out.println("搜索部分visible,更新adapter");
            adapter.setData(categories, keyword); //searchFragment onResume状态下重复搜索

        }
    }

    @Override
    public void onResume() {
//        System.out.println("搜索部分onResume");
        adapter.setData(categories,keyword); //第一次进入时 setdata
        visible = true;
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        visible = false;
//        System.out.println("搜索部分onPause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        System.out.println("搜索部分onDestroyView");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    //    return super.onCreateView(inflater, container, savedInstanceState);
//        System.out.println("搜索fragment创建");
        if(view == null){
//            System.out.println("SearchFragment view为空");
            view = inflater.inflate(R.layout.fragment_search, container,false);
            initView();
            initSet();
        }
        else{
//            System.out.println("SearchFragment view非空 不需要重新设置");
        }
        //view = inflater.inflate(R.layout.fragment_home, container,false);
        return view;
    }



    public void initView(){
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
    }

    public void initSet(){
        adapter = new MyPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.setData(categories,"");
        //TODO: 没有传入keyword
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
