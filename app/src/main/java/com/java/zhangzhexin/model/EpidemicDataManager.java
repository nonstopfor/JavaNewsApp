package com.java.zhangzhexin.model;

import java.util.ArrayList;
import java.util.List;

public class EpidemicDataManager {
    List<EpidemicDataCard> epidemicDataCards;
    List<EpidemicDataCard> countryCards;
    List<EpidemicDataCard> provinceCards;

    public EpidemicDataManager() {
        epidemicDataCards = new ArrayList<>();
    }

    public void refresh() throws InterruptedException {
        epidemicDataCards = UrlManager.getLatestEpidemicData();
        for (EpidemicDataCard card : epidemicDataCards) {
            if (card.province == null) {
                countryCards.add(card);
            } else if (card.county == null && card.country.equals("China")) {
                provinceCards.add(card);
            }
        }
    }

    public List<EpidemicDataCard> getCountryCards() {
        return countryCards;
    }

    public List<EpidemicDataCard> getProvinceCards() {
        return provinceCards;
    }

}
