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
    public List<SingleNews> getNewsList(String type, int page, int size) {
        StringBuilder url = new StringBuilder("https://covid-dashboard.aminer.cn/api/events/list?");
        url.append("type=").append(type);
        url.append("&page=").append(page);
        url.append("&size=").append(size);
        String data = readUrl(url.toString());
        JsonObject jsonData = new JsonParser().parse(data).getAsJsonObject();
        JsonArray jsonArray = jsonData.getAsJsonArray("data");
        List<SingleNews>result = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); ++i) {
            JsonObject obj = (JsonObject) jsonArray.get(i);
            result.add(new SingleNews(obj));
//            System.out.println(obj.get("content"));
//            System.out.println(obj.get("lang"));
//            System.out.println(obj.get("source"));
        }
        return result;
    }

    public String readUrl(String url) {
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
