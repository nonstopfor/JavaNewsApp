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
    public static List<NewsCard> getNewsList(String type, int page, int size) {
        StringBuilder url = new StringBuilder("https://covid-dashboard.aminer.cn/api/events/list?");
        url.append("type=").append(type);
        url.append("&page=").append(page);
        url.append("&size=").append(size);
        String data = readUrl(url.toString());
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        JsonArray jsonArray = jsonData.getAsJsonArray("data");
        List<NewsCard>result = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            result.add(new NewsCard(obj));
//            System.out.println(obj.get("source"));
        }
        return result;
    }

    public static List<NewsCard> getAllNews(){
        String url="https://covid-dashboard.aminer.cn/api/dist/events.json";
        String data = readUrl(url);
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        JsonArray jsonArray = jsonData.getAsJsonArray("data");
        List<NewsCard>result = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            result.add(new NewsCard(obj));
//            System.out.println(obj.get("source"));
        }
        return result;
    }

    public static List<NewsCard> getAllNews(String type){
        String url="https://covid-dashboard.aminer.cn/api/dist/events.json";
        String data = readUrl(url);
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        JsonArray jsonArray = jsonData.getAsJsonArray("data");
        List<NewsCard>result = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            if(!obj.get("type").toString().equals(type)) continue;
            result.add(new NewsCard(obj));
//            System.out.println(obj.get("source"));
        }
        return result;
    }

    public static String readUrl(String url) {
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
        return json.toString();
    }
}
