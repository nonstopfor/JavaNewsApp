package com.java.zhangzhexin.model;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.listener.BarLineChartTouchListener;

public class EpidemicDataBarChart extends BarChart {

    PointF downPoint = new PointF();

    public EpidemicDataBarChart(Context context) {
        super(context);
    }

    public EpidemicDataBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EpidemicDataBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setTouchListener() {
        this.setOnTouchListener(new BarLineChartTouchListener(this, this.getViewPortHandler().getMatrixTouch(), 0.1f) {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        return super.onTouch(v, event);
                    case MotionEvent.ACTION_UP:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        return super.onTouch(v, event);
                    default:
                        return super.onTouch(v, event);
                }
            }
        });
    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        getParent().requestDisallowInterceptTouchEvent(true);
//        return super.dispatchTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent evt) {
//        switch (evt.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downPoint.x = evt.getX();
//                downPoint.y = evt.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
////                Log.i("getScrollX ", getScrollX() + "");
//                if (getScaleX() > 1 && Math.abs(evt.getX() - downPoint.x) > 5) {
//                    System.out.println("in onTouchEvent");
//                    getParent().requestDisallowInterceptTouchEvent(true);
//                }
//                break;
//        }
//        return true;
//    }
}
