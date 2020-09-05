package com.java.zhangzhexin.set;

import android.content.Intent;

import com.cheng.channel.Channel;
import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.MainActivity;
import com.java.zhangzhexin.model.Tab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SetChannelPresenter extends BasePresenter<SetChannelView> {
    private Tab tab = Tab.getInstance();  //更新tab
    private LinkedHashMap<String, List<Channel>> data;
//    private List<Channel> checkedChannelList;
//    private List<Channel> uncheckedChannelList;

    public SetChannelPresenter(){
        data = new LinkedHashMap<>();
    }

    public LinkedHashMap<String,List<Channel>> getData(){
        List<Channel> checkedChannelList = new ArrayList<>();
        List<Channel> uncheckedChannelList = new ArrayList<>();
        for(String tab:tab.getTabs()){
            checkedChannelList.add(new Channel(tab,1));
        }
        for(String tab:tab.getComplementTabs()){
            uncheckedChannelList.add(new Channel(tab,1));
        }
        data.put("我的频道",checkedChannelList);
        data.put("未选频道",uncheckedChannelList);
        return data;
    }

    public void back(){
        Intent intent = new Intent(myView.getMyContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        myView.start(intent);
    }

    //点击返回键时 更新Tab的内容
    public void updateTab(List<Channel>checkedChannelList){
        List<String> checkedTabs = new ArrayList<>();
        for(Channel channel : checkedChannelList){
            checkedTabs.add(channel.getChannelName());
        }
        tab.setTabs(checkedTabs);
    }
}
