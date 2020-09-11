package com.java.zhangzhexin.overview.newslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.overview.ListAdapter;

public class NewsAdapter extends ListAdapter<NewsAdapter.ViewHolder,NewsCard> {

    private Context myContext;
    private String type;

    public NewsAdapter(Context myContext,String type){
        this.myContext = myContext;
        this.type = type;
    }

    public boolean isEmpty(){
        return data == null;
    }


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

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.news_title.setText(data.get(position).title);
        if(!type.equals("history") && data.get(position).visited())
            holder.news_title.setTextColor(myContext.getResources().getColor(R.color.colorReadNews));
        holder.news_date.setText(data.get(position).time);
        holder.news_source.setText(data.get(position).source);
    }

    @Override
    public int getItemCount() {
        if(isEmpty()) {
            return 0;
        }
        else {
            return data.size();
        }
    }
}
