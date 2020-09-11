package com.java.zhangzhexin.model;

import com.google.gson.internal.$Gson$Types;

import java.util.ArrayList;
import java.util.List;

public class ScholarManager extends BaseManager {
    public static List<ScholarCard> scholarCardListPassed;
    public static List<ScholarCard> scholarCardListNotPassed;

    int cur = 0;
    String type;

    public ScholarManager(String type) {
        this.type = type;
    }

    public List<ScholarCard> refresh(int size) throws InterruptedException {
        if (type.equals("高关注学者")) {
            scholarCardListNotPassed = UrlManager.getScholars(type);
            for (int i = 0; i < scholarCardListNotPassed.size(); ++i) {
                scholarCardListNotPassed.get(i).idx = i;
            }
        } else {
            scholarCardListPassed = UrlManager.getScholars(type);
            for (int i = 0; i < scholarCardListPassed.size(); ++i) {
                scholarCardListPassed.get(i).idx = i;
            }
        }

        cur = 0;
        return getMoreScholars(size);
    }

    public List<ScholarCard> getMoreScholars(int size) {
        int to = cur + size;
        List<ScholarCard> result = new ArrayList<>();
        if (type.equals("高关注学者")) {
            for (int i = cur; i < to; ++i) {
                if (i >= scholarCardListNotPassed.size()) break;
                result.add(scholarCardListNotPassed.get(i));
            }
        } else {
            for (int i = cur; i < to; ++i) {
                if (i >= scholarCardListPassed.size()) break;
                result.add(scholarCardListPassed.get(i));
            }
        }

        cur = to;
        return result;
    }

    public static ScholarCard getScholar(int idx, boolean passed) {
        if (passed) {
            return scholarCardListPassed.get(idx);
        } else {
            return scholarCardListNotPassed.get(idx);
        }
    }
}
