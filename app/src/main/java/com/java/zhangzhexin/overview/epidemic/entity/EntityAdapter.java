package com.java.zhangzhexin.overview.epidemic.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.EntityCard;
import com.java.zhangzhexin.model.NewsCard;
import com.java.zhangzhexin.overview.ListAdapter;

public class EntityAdapter extends ListAdapter<EntityAdapter.ViewHolder, EntityCard> {

    private Context myContext;
    private String type;

    public EntityAdapter(Context myContext, String type){
        this.myContext = myContext;
        this.type = type;
    }

    public boolean isEmpty(){
        return data == null;
    }




    //FIXME：用户拖拽屏幕过程中算点击吗?
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView entity_label;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            entity_label = itemView.findViewById(R.id.entity_label);
//            news_title = itemView.findViewById(R.id.news_title);
//            news_source = itemView.findViewById(R.id.news_source);
//            news_date = itemView.findViewById(R.id.news_date);
        }
    }

    @NonNull
    @Override
    public EntityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entity_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EntityAdapter.ViewHolder holder, int position) {
        holder.entity_label.setText(data.get(position).label);
//        holder.news_title.setText(data.get(position).title);
//        if(!type.equals("history") && data.get(position).visited())
//            holder.news_title.setTextColor(myContext.getResources().getColor(R.color.colorReadNews));
//        holder.news_date.setText(data.get(position).time);
//        holder.news_source.setText(data.get(position).source);
        //TODO:把newsitem修改一下
    }

    @Override
    public int getItemCount() {
        if(isEmpty()) {
            System.out.println("EntityAdapter : "+type+" data为空， ItemCount return 0");
            return 0;
        }
        else
            return data.size();
    }
}
