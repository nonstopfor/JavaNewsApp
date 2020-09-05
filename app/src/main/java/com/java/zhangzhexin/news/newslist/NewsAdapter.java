package com.java.zhangzhexin.news.newslist;

import android.content.Context;
import android.speech.tts.TextToSpeech;
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
    private List<NewsCard> data = null;
    private Context myContext;

    public NewsAdapter(Context myContext){
        this.myContext = myContext;
    }

    public boolean isEmpty(){
        return data == null;
    }

    //设置newslist显示的新闻
    public void resetData(List<NewsCard> data){
        this.data = data;
        notifyDataSetChanged();//刷新显示数据
    }

    public NewsCard getNews(int position){
        return data.get(position);
    }

    public void appendData(List<NewsCard> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    //FIXME：用户拖拽屏幕过程中算点击吗?
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView news_title;
        TextView news_source;
        TextView news_date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            news_title = itemView.findViewById(R.id.news_title);
            news_source = itemView.findViewById(R.id.news_source);
            news_date = itemView.findViewById(R.id.news_date);
        }
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.news_title.setText(data.get(position).title);
        if(data.get(position).visited())
            holder.news_title.setTextColor(myContext.getResources().getColor(R.color.colorReadNews));
        holder.news_date.setText(data.get(position).time);
        holder.news_source.setText(data.get(position).source);
        //TODO:把newsitem修改一下
    }

    @Override
    public int getItemCount() {
        if(isEmpty()) {
            System.out.println("NewsAdapter data为空， ItemCount return 0");
            return 0;
        }
        else
            return data.size();
    }
}
