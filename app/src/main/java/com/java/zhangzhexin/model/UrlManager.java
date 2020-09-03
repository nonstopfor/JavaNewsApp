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
        List<NewsCard>result = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            result.add(new NewsCard(obj));
//            System.out.println(obj.get("source"));
        }
        return result;
    }

    public static List<NewsCard> getAllNews() throws InterruptedException {
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

    public static List<NewsCard> getAllNews(String type) throws InterruptedException {
        String url="https://covid-dashboard.aminer.cn/api/dist/events.json";
        String data = readUrl(url);
        System.out.println(data);
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        JsonArray jsonArray = jsonData.getAsJsonArray("datas");
        List<NewsCard>result = new ArrayList<>();
//        System.out.println("type:"+type);
        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject obj = (JsonObject) jsonArray.get(i);

            if(!obj.get("type").toString().equals(type)) continue;
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
}
