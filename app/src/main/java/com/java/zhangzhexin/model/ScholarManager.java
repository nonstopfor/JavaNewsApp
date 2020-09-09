package com.java.zhangzhexin.model;

import java.util.List;

public class ScholarManager {
    List<ScholarCard> scholarCardList;

    public List<ScholarCard> refresh() throws InterruptedException {
        scholarCardList = UrlManager.getScholars();
        return scholarCardList;
    }
}
