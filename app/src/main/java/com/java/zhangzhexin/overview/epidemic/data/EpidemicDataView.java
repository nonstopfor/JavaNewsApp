package com.java.zhangzhexin.overview.epidemic.data;

import com.java.zhangzhexin.BaseView;
import com.java.zhangzhexin.model.EpidemicDataCard;

import java.util.List;

public interface EpidemicDataView extends BaseView {

    void resetEpidemicCountryData(List<EpidemicDataCard> epidemicDataCardList);

    void resetEpidemicProvinceData(List<EpidemicDataCard> epidemicDataCardList);
}
