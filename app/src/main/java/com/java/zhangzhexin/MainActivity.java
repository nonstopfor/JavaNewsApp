package com.java.zhangzhexin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
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
            //transaction.hide(newsFragment); //default显示新闻首页
        }
        if(historyFragment==null){
            historyFragment = NewsListFragment.newInstance();
            transaction.add(R.id.frameLayout,historyFragment);
            transaction.hide(historyFragment);
        }

        transaction.commit();
        currentFragment = newsFragment; //default是新闻首页
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();//创建fragment

        bottomNavigationView = findViewById(R.id.nav_view);

        //监听点击切换
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
            }
        });
        //bottomNavigationView.setSelectedItemId(R.id.home);//设置default的item
        //getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,newsFragment).commit();
        //setContentView(R.layout.news_page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();

        //监听搜索框关闭
        //FIXME: X点第一次是清空 第二次是退出
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                System.out.println("关闭搜索框");
                //TODO:
                return false;
            }
        });

        //监听搜索框内容变化
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                System.out.println("搜索关键字="+query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //System.out.println("搜索newText="+newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}