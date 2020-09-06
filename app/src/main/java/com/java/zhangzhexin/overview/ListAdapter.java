package com.java.zhangzhexin.overview;

import androidx.recyclerview.widget.RecyclerView;

import com.java.zhangzhexin.model.BaseCard;

import java.util.List;

abstract public class ListAdapter<VH extends RecyclerView.ViewHolder,MyCard extends BaseCard> extends RecyclerView.Adapter<VH>{
    protected List<MyCard> data;
    //设置newslist显示的新闻


    public void resetData(List<MyCard> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public MyCard getData(int position){
        return data.get(position);
    }

    public void appendData(List<MyCard> data){
//        System.out.println("ListAdapter进入appendData");
//        System.out.println("新数据为"+data);
        this.data.addAll(data);
        notifyDataSetChanged();
    }

}
