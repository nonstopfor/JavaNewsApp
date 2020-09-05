package com.java.zhangzhexin.set;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cheng.channel.Channel;
import com.cheng.channel.ChannelView;
import com.java.zhangzhexin.BaseActivity;
import com.java.zhangzhexin.R;

import java.util.LinkedHashMap;
import java.util.List;

public class SetChannelActivity extends BaseActivity<SetChannelView, SetChannelPresenter> implements SetChannelView {
    private ChannelView channelView;
    //private List<Channel> checkedChannelList;
    //private List<Channel> uncheckedChannelList;
    private LinkedHashMap<String, List<Channel>> data;
    private MyStyleAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("分类列表onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        channelView = findViewById(R.id.channelView);
        channelView.setChannelFixedCount(0); //设置频道数
        adapter = new MyStyleAdapter();
        //channelView.setStyleAdapter(adapter); //设置adapter

//        channelView.setOnChannelListener(new ChannelListenerAdapter() {
//            @Override
//            public void channelItemClick(int position, Channel channel) {
//                System.out.println(position + ".." + channel);
//            }
//
//            @Override
//            public void channelEditStateItemClick(int position, Channel channel) {
//                System.out.println("EditState: "+position + ".." + channel);
//            }
//
//            @Override
//            public void channelEditFinish(List<Channel> channelList) {
//                System.out.println(channelList.toString());
//                System.out.println(channelView.isChange() + "");
//                System.out.println(channelView.getOtherChannel().toString());
//            }
//
//            @Override
//            public void channelEditStart() {
//                System.out.println("channelEditStart");
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        data = myPresenter.getData();
        adapter.setData(data);
        channelView.setStyleAdapter(adapter); //没有notify 只能每次重新set
        System.out.println("分类列表getData");
    }

    //点击返回键
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            myPresenter.updateTab(channelView.getMyChannel());
            myPresenter.back();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public SetChannelPresenter createPresenter() {
        return new SetChannelPresenter();
    }

    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    @Override
    public Context getMyContext() {
        return this;
    }
}
