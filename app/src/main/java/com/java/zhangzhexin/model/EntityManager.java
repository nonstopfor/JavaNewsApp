package com.java.zhangzhexin.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityManager extends BaseManager {
    public static List<EntityCard> entityCardList;


    public List<EntityCard> getEntityCardList(String keyword) throws InterruptedException {
        String url = "https://innovaapi.aminer.cn/covid/api/v1/pneumonia/entityquery?entity=" + keyword;
        String data = UrlManager.readUrl(url);
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        JsonArray allEntities = jsonData.get("data").getAsJsonArray();

        List<EntityCard> results = new ArrayList<>();

        for (int i = 0; i < allEntities.size(); ++i) {
            JsonObject obj = (JsonObject) (allEntities.get(i));
            float hot = obj.get("hot").getAsFloat();
            String label = obj.get("label").getAsString();
            String imgUrl = readString(obj.get("img"));
            JsonObject info = obj.get("abstractInfo").getAsJsonObject();
            String description = "";
            String enwiki = info.get("enwiki").getAsString();
            String baidu = info.get("baidu").getAsString();
            String zhwiki = info.get("zhwiki").getAsString();
            if (enwiki.length() > 0) description = enwiki;
            if (baidu.length() > 0) description = baidu;
            if (zhwiki.length() > 0) description = zhwiki;

            JsonObject covid = info.get("COVID").getAsJsonObject();
            JsonObject properties = covid.get("properties").getAsJsonObject();
            Map<String, String> map = new HashMap<>();
            for (String key : properties.keySet()) {
                String value = properties.get(key).getAsString();
                map.put(key, value);
            }
            JsonArray relations = covid.get("relations").getAsJsonArray();
            List<Relation> relationList = new ArrayList<>();
            for (int j = 0; j < relations.size(); ++j) {
                JsonObject relation = (JsonObject) relations.get(j);
                String r = relation.get("relation").getAsString();
                String la = relation.get("label").getAsString();
                boolean forward = relation.get("forward").getAsBoolean();
//                System.out.println("relation:"+r);
                relationList.add(new Relation(r, la, forward));
            }
            EntityCard card = new EntityCard(label, description, hot, map, imgUrl, relationList);
//            card.display();
            results.add(card);
        }
//        System.out.println(results.size());
        results.sort(new Comparator<EntityCard>() {
            @Override
            public int compare(EntityCard o1, EntityCard o2) {
                if (o1.hot < o2.hot) {
                    return 1;
                }
                return -1;
            }
        });
        entityCardList = results;
        for (int i = 0; i < entityCardList.size(); ++i) {
            entityCardList.get(i).idx = i;
        }
        System.out.println("entityCardList size:"+entityCardList.size());
        return results;
    }

    public static String readString(JsonElement obj) {
        if (obj.isJsonNull()) {
            return null;
        }
        return obj.getAsString();
    }

    public static EntityCard getEntity(int idx) {
        return entityCardList.get(idx);
    }
}
