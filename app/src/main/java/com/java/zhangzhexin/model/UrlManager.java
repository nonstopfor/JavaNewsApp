package com.java.zhangzhexin.model;

import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class UrlManager {
    public static List<NewsCard> getNewsList(String type, int page, int size) throws InterruptedException {
        StringBuilder url = new StringBuilder("https://covid-dashboard.aminer.cn/api/events/list?");
        url.append("type=").append(type);
        url.append("&page=").append(page);
        url.append("&size=").append(size);
        String data = readUrl(url.toString());
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        JsonArray jsonArray = jsonData.getAsJsonArray("data");
        List<NewsCard> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            if (obj.get("content").getAsString().length() == 0) continue;
            result.add(new NewsCard(obj));
//            System.out.println(obj.get("source"));
        }
        return result;
    }

    public static List<NewsCard> getAllNews() throws InterruptedException {
        String url = "https://covid-dashboard.aminer.cn/api/dist/events.json";
        String data = readUrl(url);
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        JsonArray jsonArray = jsonData.getAsJsonArray("data");
        List<NewsCard> result = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            result.add(new NewsCard(obj));
//            System.out.println(obj.get("source"));
        }
        return result;
    }

    public static List<NewsCard> getAllNews(String type) throws InterruptedException {
        String url = "https://covid-dashboard.aminer.cn/api/dist/events.json";
        String data = readUrl(url);
//        System.out.println(data);
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        JsonArray jsonArray = jsonData.getAsJsonArray("datas");
        List<NewsCard> result = new ArrayList<>();
        System.out.println("type:" + type);
        System.out.println("jsonArray.size:" + jsonArray.size());
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject obj = (JsonObject) jsonArray.get(i);
//            System.out.println(obj.get("type").getAsString());
            if (!(obj.get("type").getAsString().equals(type))) continue;
//            System.out.println(obj.get("type").getAsString());
            result.add(new NewsCard(obj));
//            System.out.println(obj.get("source"));
        }
        System.out.println("in getAllNews result size:" + result.size());
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
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        return new SingleNews(jsonData.getAsJsonObject("data"));
    }

    public static List<EpidemicDataCard> getLatestEpidemicData() throws InterruptedException {
        List<EpidemicDataCard> result = new ArrayList<>();
        String url = "https://covid-dashboard.aminer.cn/api/dist/epidemic.json";
        String data = readUrl(url);
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
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

    public static Integer readInt(JsonElement obj){
        if(obj.isJsonNull()){
            return null;
        }
        return obj.getAsInt();
    }
}
