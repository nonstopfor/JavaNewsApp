package com.java.zhangzhexin.detail;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

import com.java.zhangzhexin.HomeFragment;
import com.java.zhangzhexin.MainActivity;
import com.java.zhangzhexin.R;
import com.java.zhangzhexin.detail.entitydetail.EntityDetailFragment;
import com.java.zhangzhexin.detail.newsdetail.NewsDetailFragment;
import com.java.zhangzhexin.model.Tab;


/*
FIXME:
感觉详情页不需要fragment的切换
可以根据不同type 展开不同的type_detail.xml?
可能参数有些不同 具体处理
可以定义
一个abstract的参数提取
一个abstract的参数设置函数

先写完News的详情页 之后再决定如何扩展到其他的详情页
*/
public class DetailActivity extends AppCompatActivity {

    private NewsDetailFragment newsDetailFragment;
    private EntityDetailFragment entityDetailFragment;
    private Fragment currentFragment;
    private String type;

    public void switchFragment(Fragment target) {
        if (currentFragment != target) {
            System.out.println("当前fragment为 " + currentFragment + ", 切换到fragment = " + target);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(currentFragment).show(target);
            transaction.setMaxLifecycle(currentFragment, Lifecycle.State.STARTED).setMaxLifecycle(target, Lifecycle.State.RESUMED);
            transaction.commit();
            currentFragment = target;
        }
    }

    public void initFragment(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(newsDetailFragment==null){
            newsDetailFragment = NewsDetailFragment.newInstance();
            transaction.add(R.id.frameLayout, newsDetailFragment); //默认显示newsDetail
            System.out.println("finish construct newsDetailFragment");
        }
        if(entityDetailFragment==null){
            entityDetailFragment = EntityDetailFragment.newInstance();
            transaction.add(R.id.frameLayout,entityDetailFragment);
            System.out.println("finish construct entityDetailFragment");
        }
        transaction.commit();
    }

    //TODO：onCreate重写
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("详情页onCreate");
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detail);
        initFragment();
        System.out.println("详情页离开onCreate");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        type = getIntent().getStringExtra("type");
        if(type.equals("news"))
        {
            String news_id = getIntent().getStringExtra("news_id");
            newsDetailFragment.setId(news_id);
        }
        else if(type.equals("entity")){ //实体
            int entity_id = getIntent().getIntExtra("entity_id",-1);
            System.out.println("得到实体id = "+entity_id);
            entityDetailFragment.setId(entity_id);
        }
        else{
            //TODO:学者
        }
        //TODO:根据intent传来的信息选择启动哪个detail fragment
//        news_id = getIntent().getStringExtra("news_id");
//        System.out.println("详情页收到新的news_id = "+news_id);
//        myPresenter.setNews(news_id);
        //TODO:调用presenter接口实现 返回一个SingleNews
    }

    //需要在resume里getIntent
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }



}
