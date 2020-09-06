package com.java.zhangzhexin.overview.epidemic.data;

import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.model.EpidemicDataManager;

public class EpidemicDataPresenter extends BasePresenter<EpidemicDataView> {
    private EpidemicDataManager epidemicDataManager;

    public EpidemicDataPresenter(){
        epidemicDataManager = new EpidemicDataManager();
    }

    public void refresh() throws InterruptedException {
        System.out.println("疫情数据Presenter refresh!");
        epidemicDataManager.refresh();
        myView.resetEpidemicCountryData(epidemicDataManager.getCountryCards());
        myView.resetEpidemicProvinceData(epidemicDataManager.getProvinceCards());
    }
}
