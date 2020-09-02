package com.java.zhangzhexin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;

import com.java.zhangzhexin.news.NewsFragment;

public class MainActivity extends AppCompatActivity {
    private NewsFragment newsFragment;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //显示新闻列表页
        if(newsFragment==null)
            newsFragment = NewsFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,newsFragment).commit();
        //setContentView(R.layout.news_page);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search,menu);
        searchView = (SearchView) menu.findItem(R.id.searchView).getActionView();

        //FIXME:感觉延迟好大啊。。。
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