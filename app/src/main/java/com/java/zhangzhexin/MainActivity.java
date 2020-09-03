package com.java.zhangzhexin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.java.zhangzhexin.news.NewsFragment;
import com.java.zhangzhexin.news.newslist.NewsListFragment;


//FIXME: 模拟器上 切换时会崩掉
public class MainActivity extends AppCompatActivity {
    private NewsFragment newsFragment;
    private NewsListFragment historyFragment;
    private SearchView searchView;
    private BottomNavigationView bottomNavigationView;
    private Fragment currentFragment;
    private SearchHistoryProvider searchHistoryProvider;

    public void switchFragment(Fragment target){
        if(currentFragment != target){
            System.out.println("current fragment = "+currentFragment+", target fragment = "+target);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(currentFragment).show(target).commit();
            currentFragment = target;
        }
    }

    public void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(newsFragment==null){
            newsFragment = NewsFragment.newInstance();
            transaction.add(R.id.frameLayout,newsFragment);
            System.out.println("finish construct newsFragment");
            //transaction.hide(newsFragment); //default显示新闻首页
        }
        if(historyFragment==null){
            historyFragment = NewsListFragment.newInstance("news",""); //TODO:要给history一种类型
            transaction.add(R.id.frameLayout,historyFragment);
            transaction.hide(historyFragment);
            System.out.println("finish construct historyFragment");
        }

        transaction.commit();
        currentFragment = newsFragment; //default是新闻首页
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println("intent get query = "+query);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    SearchHistoryProvider.AUTHORITY, SearchHistoryProvider.MODE);
            suggestions.saveRecentQuery(query, null);
        }

        initFragment();//创建fragment

        //监听点击切换
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.home) {
                switchFragment(newsFragment);
                //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, newsFragment).commit();
                System.out.println("切换到首页");
                return true; //不return true切换时没有动画效果
            }
            else if(menuItem.getItemId() == R.id.history) {
                switchFragment(historyFragment);
                //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, historyFragment).commit();
                System.out.println("切换到浏览历史");
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setSubmitButtonEnabled(true); //添加提交按钮
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