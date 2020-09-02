package com.java.zhangzhexin.newslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.java.zhangzhexin.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private String[] data;

    public NewsAdapter(){
    }

    //设置newslist显示的新闻
    public void setData(String[] data){
        this.data = data;
        notifyDataSetChanged();//刷新显示数据
    }

    //FIXME：用户拖拽屏幕过程中算点击吗?
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView news_title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_title =itemView.findViewById(R.id.news_title);
        }
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitem, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.news_title.setText(data[position]);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }
}
