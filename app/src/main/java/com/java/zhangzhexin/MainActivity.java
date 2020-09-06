package com.java.zhangzhexin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import android.app.DownloadManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.java.zhangzhexin.model.Tab;
import com.java.zhangzhexin.overview.epidemic.data.EpidemicDataFragment;
import com.java.zhangzhexin.overview.newslist.NewsListFragment;


//FIXME: 模拟器上 切换时会崩掉
public class MainActivity extends AppCompatActivity {
    private Tab tabObject;
    private HomeFragment homeFragment;
    private NewsListFragment historyFragment;
    private SearchFragment searchFragment;
    private EpidemicDataFragment epidemicDataFragment;

    private SearchView searchView;
    private BottomNavigationView bottomNavigationView;
    private Fragment currentFragment;
    private SearchHistoryProvider searchHistoryProvider;
    private SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
            SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);

    public void switchFragment(Fragment target){
        if(currentFragment != target){
            System.out.println("当前fragmen为 "+currentFragment+", 切换到fragment = "+target);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(currentFragment).show(target);
            transaction.setMaxLifecycle(currentFragment, Lifecycle.State.STARTED).setMaxLifecycle(target, Lifecycle.State.RESUMED);
            transaction.commit();
            currentFragment = target;
            //处于搜索页面时显示返回键
            getSupportActionBar().setDisplayHomeAsUpEnabled(target == searchFragment);

        }

    }

    public void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(homeFragment ==null){
            tabObject = Tab.getInstance();
            //System.out.println("tabs = "+tabObject.tabs);
            homeFragment = HomeFragment.newInstance(tabObject);
            transaction.add(R.id.frameLayout, homeFragment);
            
            System.out.println("finish construct newsFragment");
            //transaction.hide(newsFragment); //default显示新闻首页
        }
        if(historyFragment==null){
            historyFragment = NewsListFragment.newInstance("history",""); //TODO:要给history一种类型
            transaction.add(R.id.frameLayout,historyFragment).setMaxLifecycle(historyFragment, Lifecycle.State.STARTED);
            transaction.hide(historyFragment);
            System.out.println("finish construct historyFragment");
        }

        if(searchFragment==null){
            searchFragment = SearchFragment.newInstance();
            transaction.add(R.id.frameLayout,searchFragment).setMaxLifecycle(searchFragment, Lifecycle.State.STARTED);
            transaction.hide(searchFragment);
            System.out.println("finish construct searchFragment");
        }

        if(epidemicDataFragment==null){
            epidemicDataFragment = EpidemicDataFragment.newInstance();
            transaction.add(R.id.frameLayout,epidemicDataFragment).setMaxLifecycle(epidemicDataFragment, Lifecycle.State.STARTED);
            transaction.hide(epidemicDataFragment);
            System.out.println("finish construct epidemicDataFragment");
        }

        transaction.commit();
        currentFragment = homeFragment; //default是新闻首页
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("糟糕！进入MainActivity onCreate!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //在submitLisenter中捕获 防止activity跳转和重建
//        Intent intent = getIntent();
//        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
//            System.out.println("收到查询关键字 = "+query);
//            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
//                    SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
//            suggestions.saveRecentQuery(query, null);
//
//            switchFragment(searchFragment);//切换到searchFragment
//            //启动SearchActivity
////            Intent searchIntent = new Intent(this,SearchActivity.class);
////            startActivity(searchIntent);
//        }

        initFragment();//创建fragment

        //监听点击切换
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.home) {
                switchFragment(homeFragment);
                //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, newsFragment).commit();
                System.out.println("切换到首页");
                return false; //不return true切换时没有动画效果
            }
            else if(menuItem.getItemId() == R.id.history) {
                switchFragment(historyFragment);
                //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, historyFragment).commit();
                System.out.println("切换到浏览历史");
                return true;
            }
            else if(menuItem.getItemId() == R.id.epidemicData){
                switchFragment(epidemicDataFragment);
                System.out.println("切换到疫情数据");
                return true;
            }
            return false;
        });

        System.out.println("离开MainActivity.onCreate");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            switchFragment(homeFragment); //sear
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        String query = intent.getStringExtra(SearchManager.QUERY);
        if(query!=null){
            //拦截query发送
            System.out.println("拦截到query = "+query);
            suggestions.saveRecentQuery(query, null);
            searchFragment.setKeyword(query); //更新关键词
            switchFragment(searchFragment);//切换到searchFragment
            searchView.setQuery("",false); //清空搜索框
        }
        else
            super.startActivityForResult(intent, requestCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //searchView.setIconifiedByDefault(true); // Do not iconify the widget; expand it by default
        searchView.setSubmitButtonEnabled(true); //添加提交按钮
        //((ImageView)searchView.findViewById(R.id.search_go_btn)).setImageResource(R.drawable.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("监听器中收到关键字 = "+query);
                return false;
//                System.out.println("收到查询关键字 = "+query);
//                suggestions.saveRecentQuery(query, null);
//                searchFragment.setKeyword(query); //更新关键词
//                searchView.clearFocus();  //可以收起键盘
//                searchView.onActionViewCollapsed();    //可以收起SearchView视图
//                switchFragment(searchFragment);//切换到searchFragment
//                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //FIXME: 搜索提交之后重新创建了MainActivity
        //监听搜索框关闭
        //FIXME: X点第一次是清空 第二次是退出
//        searchView.setOnCloseListener(() -> {
//            System.out.println("关闭搜索框");
//            //TODO:
//            return false;
//        });
//
//        //监听搜索框内容变化
//        //TODO：搜索记录实现
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                System.out.println("搜索关键字="+query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                //System.out.println("搜索newText="+newText);
//                return false;
//            }
//        });

        return true;
    }
}