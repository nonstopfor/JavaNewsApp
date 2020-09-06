package com.java.zhangzhexin.model;

import java.util.List;
import java.util.Map;

public class EntityCard extends BaseCard {
    String label;
    String description;
    double hot;
    Map properties;
    String imgUrl;
    List<Relation> relationList;

    EntityCard(String label, String description, double hot, Map properties, String imgUrl, List<Relation> relationList) {
        this.label = label;
        this.description = description;
        this.hot = hot;
        this.properties = properties;
        this.imgUrl = imgUrl;
        this.relationList = relationList;
    }
}
