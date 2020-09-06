package com.java.zhangzhexin.overview.epidemic.data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.java.zhangzhexin.BaseFragment;
import com.java.zhangzhexin.R;
import com.java.zhangzhexin.model.EpidemicDataCard;

import java.util.ArrayList;
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

        return view;
    }

    public void initView() {
        swipeRefreshLayout = view.findViewById(R.id.epidemicDataSwipeRefreshLayout);
        countryBarChart = view.findViewById(R.id.countryBarChart);
        provinceBarChart = view.findViewById(R.id.provinceBarChart);
        setCountryBarChartStyle();
        setProvinceBarChartStyle();
    }

    void setBarChartStyle(BarChart barChart) {
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setScaleEnabled(false);
        barChart.setDrawMarkers(true);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(9f);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);

        YAxis left = barChart.getAxisLeft();
        left.setAxisMinimum(0);
        YAxis right = barChart.getAxisRight();
        right.setAxisMinimum(0);
    }

    public void setCountryBarChartStyle() {
        setBarChartStyle(countryBarChart);
    }

    public void setProvinceBarChartStyle() {
        setBarChartStyle(provinceBarChart);
    }

    public void initSet() {
        swipeRefreshLayout.setOnRefreshListener(() -> {
            System.out.println("refresh!");
            swipeRefreshLayout.setRefreshing(true);
            try {
                myPresenter.refresh();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onResume() {
        System.out.println("疫情数据onResume");
        super.onResume();
        if (isFirstLoad) {
            try {
                myPresenter.refresh();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    public void drawEpidemicCountryData(List<EpidemicDataCard> cards) {
        List<BarEntry> barEntries = new ArrayList<>();
        List<String> xLabels = new ArrayList<>();
        for (int i = 0; i < cards.size(); ++i) {
            barEntries.add(new BarEntry(i, cards.get(i).confirmed));
//            System.out.println(cards[i].country);
            xLabels.add(cards.get(i).country);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Epidemic Data For Different Countries");
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.6f);
//        barChart.getXAxis().setAxisMinimum(0);
        countryBarChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                System.out.println(value);
                return cards.get((int) value).country;
            }
        });
//        barChart.getXAxis().setLabelCount(cards.size());
        countryBarChart.setMarker(new DataMarkder(getMyContext(), cards, "country"));
        countryBarChart.setData(barData);
        ViewPortHandler viewPortHandler = countryBarChart.getViewPortHandler();
        Matrix matrix = new Matrix();
        matrix.setScale(1.5f, 1.0f);

        viewPortHandler.refresh(matrix, countryBarChart, false);

        countryBarChart.invalidate();
        System.out.println("barEntry size:"+barEntries.size());
        System.out.println("finish draw epidemic country data");
    }

    public void drawEpidemicProvinceData(List<EpidemicDataCard> cards) {
        List<BarEntry> barEntries = new ArrayList<>();
        List<String> xLabels = new ArrayList<>();
        for (int i = 0; i < cards.size(); ++i) {
            barEntries.add(new BarEntry(i, cards.get(i).confirmed));
//            System.out.println(cards[i].country);
            xLabels.add(cards.get(i).province);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Epidemic Data For Different Provinces");
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.6f);
//        barChart.getXAxis().setAxisMinimum(0);
        provinceBarChart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                System.out.println(value);
                return cards.get((int) value).province;
            }
        });
//        barChart.getXAxis().setLabelCount(cards.size());
        provinceBarChart.setMarker(new DataMarkder(getMyContext(), cards, "province"));
        provinceBarChart.setData(barData);
        ViewPortHandler viewPortHandler = provinceBarChart.getViewPortHandler();
        Matrix matrix = new Matrix();
        matrix.setScale(1.5f, 1.0f);

        viewPortHandler.refresh(matrix, provinceBarChart, false);

        provinceBarChart.invalidate();
        System.out.println("finish draw epidemic province data");

    }

    class DataMarkder extends MarkerView {
        private TextView textView;
        private List<EpidemicDataCard> cards;
        private String mode;

        public DataMarkder(Context context, List<EpidemicDataCard> cards, String mode) {
            super(context, R.layout.marker_view);
            this.cards = cards;
            textView = findViewById(R.id.markerContent);
            this.mode = mode;
        }

        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            EpidemicDataCard card = cards.get((int) e.getX());
            if(mode.equals("country")){
                textView.setText(card.displayMessageCountry());
            }
            else if(mode.equals("province")){
                textView.setText(card.displayMessageProvince());
            }
            super.refreshContent(e, highlight);
        }

        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(float) (getWidth() / 2), -(float) (getHeight() / 2));
        }
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


