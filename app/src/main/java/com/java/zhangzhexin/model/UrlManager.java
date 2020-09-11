package com.java.zhangzhexin.model;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class UrlManager {
    public static List<NewsCard> getNewsList(String type, int page, int size) throws InterruptedException, IllegalStateException {
        StringBuilder url = new StringBuilder("https://covid-dashboard.aminer.cn/api/events/list?");
        url.append("type=").append(type);
        url.append("&page=").append(page);
        url.append("&size=").append(size);
        List<NewsCard> result = new ArrayList<>();

        String data = readUrl(url.toString());
        JsonObject jsonData;
        try{
            jsonData = new JsonParser().parse(data).getAsJsonObject();
        }
        catch (Exception e){
            e.printStackTrace();
            return result;
        }
        JsonArray jsonArray = jsonData.getAsJsonArray("data");

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            if (getString(obj, "content").length() == 0) continue;
            result.add(new NewsCard(obj));
//            System.out.println(obj.get("source"));
        }
        return result;
    }

    public static String readUrl(String url) throws InterruptedException {
        final String[] result = new String[1];
        Thread childThread = new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuilder json = new StringBuilder();
                try {
                    URL urlObject = new URL(url);
                    URLConnection uc = urlObject.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "utf-8"));
                    String inputLine = null;
                    while ((inputLine = in.readLine()) != null) {
                        json.append(inputLine);
                    }
                    in.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result[0] = json.toString();
//                System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
//                System.out.println("result[0]:"+result[0]);
            }
        });
        childThread.start();
        childThread.join();
//        System.out.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
//        System.out.println("result[0]:"+result[0]);
        return result[0];
    }

    public static SingleNews getSingleNewsById(String id) throws InterruptedException {
        String url = "https://covid-dashboard-api.aminer.cn/event/" + id;
        String data = readUrl(url);
        JsonObject jsonData;
        try{
            jsonData = new JsonParser().parse(data).getAsJsonObject();
        }
        catch (Exception e){
            e.printStackTrace();
            return new SingleNews();
        }
        return new SingleNews(jsonData.getAsJsonObject("data"));
    }

    public static List<EpidemicDataCard> getLatestEpidemicData() throws InterruptedException {
        List<EpidemicDataCard> result = new ArrayList<>();
        String url = "https://covid-dashboard.aminer.cn/api/dist/epidemic.json";
        String data = readUrl(url);
        JsonObject jsonData;
        try{
            jsonData = new JsonParser().parse(data).getAsJsonObject();
        }
        catch (Exception e){
            e.printStackTrace();
            return result;
        }
        for (String key : jsonData.keySet()) {
//            System.out.print(key + " ");
            JsonArray allData = jsonData.get(key).getAsJsonObject().get("data").getAsJsonArray();
            int len = allData.size();
            JsonArray newData = allData.get(len - 1).getAsJsonArray();
            Integer confirmed = readInt(newData.get(0));
            Integer suspected = readInt(newData.get(1));
            Integer cured = readInt(newData.get(2));
            Integer dead = readInt(newData.get(3));
            result.add(new EpidemicDataCard(key, confirmed, suspected, cured, dead));

//            System.out.println(key+" "+confirmed+" "+suspected+" "+cured+" "+dead);
        }
//        System.out.println(jsonData.keySet().size());
//        System.out.println(result.size());
        return result;
    }

    public static Integer readInt(JsonElement obj) {
        if (obj.isJsonNull()) {
            return null;
        }
        return obj.getAsInt();
    }

    static List<ScholarCard> getScholars() throws InterruptedException {
        String url = "https://innovaapi.aminer.cn/predictor/api/v1/valhalla/highlight/get_ncov_expers_list?v=2";
        String data = readUrl(url);
        JsonObject jsonData;
        List<ScholarCard> cards = new ArrayList<>();

        try{
            jsonData = new JsonParser().parse(data).getAsJsonObject();
        }
        catch (Exception e){
            e.printStackTrace();
            return cards;
        }
        JsonArray jsonArray = jsonData.get("data").getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject scholar = jsonArray.get(i).getAsJsonObject();
            String avatar = scholar.get("avatar").getAsString();
//            String id = scholar.get("id").getAsString();
//            String name = scholar.get("name").getAsString();
            String name = scholar.get("name_zh").getAsString();
            if (name.length() == 0) {
                name = scholar.get("name").getAsString();
            }
//            String position = scholar.get("position").getAsString();
            boolean passed = scholar.get("is_passedaway").getAsBoolean();
            JsonObject profileObj = scholar.get("profile").getAsJsonObject();
            String position = getString(profileObj, "position");
            Map<String, String> profile = new LinkedHashMap<>();
            profile.put("相关组织", profileObj.get("affiliation").getAsString());
            profile.put("简介", profileObj.get("bio").getAsString());
            profile.put("教育经历", getString(profileObj, "edu"));
            profile.put("工作经历", getString(profileObj, "work"));
            profile.put("主页", getString(profileObj, "homepage"));

            ScholarCard card = new ScholarCard(avatar, name, position, passed, profile);
//            card.display();
            cards.add(card);
        }
//        System.out.println(jsonArray.size());
        return cards;
    }

    public static String getString(JsonObject obj, String name) {
        if (obj.get(name) != null) {
            return obj.get(name).getAsString();
        }
        return "";
    }
}
