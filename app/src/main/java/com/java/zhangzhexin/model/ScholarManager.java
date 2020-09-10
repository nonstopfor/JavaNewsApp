package com.java.zhangzhexin.model;

import java.util.ArrayList;
import java.util.List;

public class ScholarManager extends BaseManager {
    public static List<ScholarCard> scholarCardList;
    int cur = 0;

    public List<ScholarCard> refresh(int size) throws InterruptedException {
        scholarCardList = UrlManager.getScholars();
        for (int i = 0; i < scholarCardList.size(); ++i) {
            scholarCardList.get(i).idx = i;
        }
        cur = 0;
        return getMoreScholars(size);
    }

    public List<ScholarCard> getMoreScholars(int size) {
        int to = cur + size;
        List<ScholarCard> result = new ArrayList<>();
        for (int i = cur; i < to; ++i) {
            if (i >= scholarCardList.size()) break;
            result.add(scholarCardList.get(i));
        }
        cur = to;
        return result;
    }

    public static ScholarCard getScholar(int idx) {
        return scholarCardList.get(idx);
    }
}
