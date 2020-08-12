package com.example.recycleviewtest;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.Inherited;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private String[] fruitlist;

    public FruitAdapter(String[] data){
        fruitlist = data;
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public ViewHolder(View item){
            super(item);
            //textView = item.findViewById(R.id.fruit_name);
            //imageView = item.findViewById(R.id.my_imageView);
            textView = item.findViewById(R.id.text_title);
            imageView = item.findViewById(R.id.image_view);
            //imageView.setImageResource(R.drawable.ic_favorite);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.qinyue_item_news,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        holder.textView.setText(fruitlist[position]);
        holder.imageView.setImageResource(R.drawable.ic_favorite);
    }

    @Override
    public int getItemCount(){
        return fruitlist.length;
    }
}
