package com.java.zhangzhexin.news.newslist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.NewsCard;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsCard> data;

    public NewsAdapter(){
    }

    //设置newslist显示的新闻
    public void resetData(List<NewsCard> data){
        this.data = data;
        notifyDataSetChanged();//刷新显示数据
    }

    public void appendData(List<NewsCard> data){
        this.data.addAll(data);
        notifyDataSetChanged();
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
        holder.news_title.setText(data.get(position).title);
        //TODO:把newsitem修改一下
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
