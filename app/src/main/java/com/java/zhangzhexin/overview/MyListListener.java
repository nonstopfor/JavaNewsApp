package com.java.zhangzhexin.overview;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//用于监听对item的点击事件
public class MyListListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private OnItemClickListener listener;

    public interface  OnItemClickListener{
        void onItemClick(View view,int position);//childview和position
    }

    public MyListListener(Context context, final RecyclerView recyclerView, final OnItemClickListener listener){
        this.listener = listener;
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if(childView != null && listener != null){
                    listener.onItemClick(childView,recyclerView.getChildLayoutPosition(childView));
                    return true;
                }
                return false;
                //return super.onSingleTapUp(e);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        if(gestureDetector.onTouchEvent(e))
            return true;
        else
            return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}
