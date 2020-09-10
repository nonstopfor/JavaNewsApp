package com.java.zhangzhexin.model;

import java.util.List;

public class ScholarManager extends BaseManager {
    public static List<ScholarCard> scholarCardList;

    public List<ScholarCard> refresh() throws InterruptedException {
        scholarCardList = UrlManager.getScholars();
        for (int i = 0; i < scholarCardList.size(); ++i) {
            scholarCardList.get(i).idx = i;
        }
        return scholarCardList;
    }

    public static ScholarCard getScholar(int idx) {
        return scholarCardList.get(idx);
    }
}
