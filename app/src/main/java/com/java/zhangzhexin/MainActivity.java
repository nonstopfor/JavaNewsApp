package com.java.zhangzhexin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private NewsFragment newsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(newsFragment==null)
            newsFragment = NewsFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,newsFragment).commit();
        //setContentView(R.layout.news_page);
    }
}