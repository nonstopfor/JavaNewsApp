package com.java.zhangzhexin.overview.epidemic.data;

import com.java.zhangzhexin.BasePresenter;
import com.java.zhangzhexin.model.EpidemicDataManager;

public class EpidemicDataPresenter extends BasePresenter<EpidemicDataView> {
    private EpidemicDataManager epidemicDataManager;

    public EpidemicDataPresenter(){
        epidemicDataManager = new EpidemicDataManager();
    }

    public void refresh() {
        //System.out.println("疫情数据Presenter refresh!");
        try {
            epidemicDataManager.refresh();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myView.resetEpidemicCountryData(epidemicDataManager.getCountryCards());
        myView.resetEpidemicProvinceData(epidemicDataManager.getProvinceCards());
    }
}
