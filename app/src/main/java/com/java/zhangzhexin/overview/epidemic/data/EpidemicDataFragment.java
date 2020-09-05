package com.java.zhangzhexin.overview.epidemic.data;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.java.zhangzhexin.BaseFragment;
import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.EpidemicDataCard;

import java.util.List;

public class EpidemicDataFragment extends BaseFragment<EpidemicDataView, EpidemicDataPresenter> implements EpidemicDataView {

    private View view = null;
    private BarChart countryBarChart;
    private BarChart provinceBarChart;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isFirstLoad = true;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        System.out.println("EpidemicDataFragment onCreateView!");
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_epidemicdata, container, false);
            initView();
            initSet();
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void initView() {
        swipeRefreshLayout = view.findViewById(R.id.epidemicDataSwipeRefreshLayout);
        countryBarChart = view.findViewById(R.id.countryBarChart);
        provinceBarChart = view.findViewById(R.id.provinceBarChart);
        setCountryBarChartStyle();
        setProvinceBarChartStyle();
    }

    public void setCountryBarChartStyle() {

    }

    public void setProvinceBarChartStyle() {

    }

    public void initSet() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            System.out.println("refresh!");
            swipeRefreshLayout.setRefreshing(true);
            myPresenter.refresh();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            myPresenter.refresh();
            isFirstLoad = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isFirstLoad = true; //重置
    }

    public static EpidemicDataFragment newInstance() {

        Bundle args = new Bundle();

        EpidemicDataFragment fragment = new EpidemicDataFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void resetEpidemicCountryData(List<EpidemicDataCard> epidemicDataCardList) {
        drawEpidemicCountryData(epidemicDataCardList);
    }

    @Override
    public void resetEpidemicProvinceData(List<EpidemicDataCard> epidemicDataCardList) {
        drawEpidemicProvinceData(epidemicDataCardList);
    }

    public void drawEpidemicCountryData(List<EpidemicDataCard> epidemicDataCardList) {

    }

    public void drawEpidemicProvinceData(List<EpidemicDataCard> epidemicDataCardList) {

    }


    @Override
    public void start(Intent intent) {
        startActivity(intent);
    }

    @Override
    public Context getMyContext() {
        return getContext();
    }


    @Override
    public EpidemicDataPresenter createPresenter() {
        return new EpidemicDataPresenter();
    }
}
