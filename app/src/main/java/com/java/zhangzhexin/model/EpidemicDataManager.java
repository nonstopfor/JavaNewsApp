package com.java.zhangzhexin.model;

import java.util.ArrayList;
import java.util.List;

public class EpidemicDataManager {
    List<EpidemicDataCard> epidemicDataCards;
    List<EpidemicDataCard> countryCards;
    List<EpidemicDataCard> provinceCards;

    public EpidemicDataManager() {
        epidemicDataCards = new ArrayList<>();
        countryCards = new ArrayList<>();
        provinceCards = new ArrayList<>();
    }

    public void refresh() throws InterruptedException {
        List<EpidemicDataCard> temp = UrlManager.getLatestEpidemicData();
        if (temp.isEmpty()) return;
        epidemicDataCards = temp;
        countryCards.clear();
        provinceCards.clear();
        if (epidemicDataCards.isEmpty()) throw new InterruptedException();
        for (EpidemicDataCard card : epidemicDataCards) {
            if (card.province == null && !card.country.equals("World")) {
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
