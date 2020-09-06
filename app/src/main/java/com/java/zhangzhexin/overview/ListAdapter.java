package com.java.zhangzhexin.overview;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.java.zhangzhexin.R;
import com.java.zhangzhexin.detail.newsdetail.NewsDetailActivity;
import com.java.zhangzhexin.model.BaseCard;
import com.java.zhangzhexin.model.NewsCard;

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
        data.addAll(data);
        notifyDataSetChanged();
    }


}
