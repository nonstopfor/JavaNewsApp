package com.java.zhangzhexin.set;

import com.cheng.channel.Channel;
import com.cheng.channel.DefaultStyleAdapter;

import java.util.LinkedHashMap;
import java.util.List;

public class MyStyleAdapter extends DefaultStyleAdapter {
    private LinkedHashMap<String,List<Channel>> data;

    public void setData(LinkedHashMap<String, List<Channel>> data) {
        this.data = data;
    }

    @Override
    public LinkedHashMap<String, List<Channel>> getChannelData() {
        return data;
    }
}
