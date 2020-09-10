package com.java.zhangzhexin.overview.epidemic.scholar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.ScholarCard;
import com.java.zhangzhexin.overview.ListAdapter;

public class ScholarAdapter extends ListAdapter<ScholarAdapter.ViewHolder, ScholarCard> {

    private Context myContext;
    private String type;

    public ScholarAdapter(Context myContext, String type){
        this.myContext = myContext;
        this.type = type;
    }

    public boolean isEmpty(){
        return data == null;
    }

    //FIXME：用户拖拽屏幕过程中算点击吗?
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        TextView position;
        TextView name;
        TextView organization;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            position = itemView.findViewById(R.id.position);
            name = itemView.findViewById(R.id.name);
            organization = itemView.findViewById(R.id.organization);
//            entity_label = itemView.findViewById(R.id.entity_label);
//            news_title = itemView.findViewById(R.id.news_title);
//            news_source = itemView.findViewById(R.id.news_source);
//            news_date = itemView.findViewById(R.id.news_date);
        }
    }

    @NonNull
    @Override
    public ScholarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scholar, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScholarAdapter.ViewHolder holder, int position) {
        Glide.with(myContext).load(data.get(position).avatar).into(holder.photo);
        holder.position.setText(data.get(position).position);
        holder.name.setText(data.get(position).name);
        System.out.println("organization = "+data.get(position).profile.get("相关组织"));
        holder.organization.setText(data.get(position).profile.get("相关组织"));
    }

    @Override
    public int getItemCount() {
        if(isEmpty()) {
            //System.out.println("EntityAdapter : "+type+" data为空， ItemCount return 0");
            return 0;
        }
        else
            return data.size();
    }
}
