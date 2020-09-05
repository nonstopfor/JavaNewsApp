package com.java.zhangzhexin.model;

import java.util.ArrayList;
import java.util.List;

public class EpidemicDataManager {
    List<EpidemicDataCard> epidemicDataCards;

    public EpidemicDataManager() {
        epidemicDataCards = new ArrayList<>();
    }

    public void refresh() throws InterruptedException {
        epidemicDataCards = UrlManager.getLatestEpidemicData();
    }


}
