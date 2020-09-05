package com.java.zhangzhexin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.java.zhangzhexin.model.Tab;
import com.java.zhangzhexin.overview.newslist.NewsListFragment;

import java.util.List;

import com.java.zhangzhexin.set.SetChannelActivity;

public class NewsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter adapter;
    private Tab tabObject;
    private ImageView editButton;

    private View view;

    public NewsFragment(Tab tabObject){
        this.tabObject = tabObject;
        System.out.println("构造NewsFragment, tabs = "+tabObject.getTabs());
        //this.categories = tabObject.getTabs();
        //this.categories = new ArrayList<>(Arrays.asList("news","paper","news","paper","news","paper","news","paper","news","paper","news","paper"));
        //System.out.println("categories = "+categories);
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("NewsFragment进入onResume");
        updateCategories();
        System.out.println("更新分类完毕");
    }

    public void updateCategories(){
        if(adapter != null) {
            adapter.setData(tabObject.getTabs());
        }
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
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            //TODO: update一次 之后notify一下adapter
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

        private List<String> data;

        public MyPagerAdapter(@NonNull FragmentManager fm,int behavior) {
            super(fm,behavior);
            System.out.println("");
        }

        public void setData(List<String> data){
            this.data = data;
            notifyDataSetChanged();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            //System.out.println("category = "+categories.get(position));
            return data.get(position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            System.out.println("getItem: type = "+data.get(position)+" keyword = "+"");
            return NewsListFragment.newInstance(data.get(position),"");
        }

        @Override
        public int getCount() {
            if(data == null)
                return 0;
            else
                return data.size();
        }
    }


}

