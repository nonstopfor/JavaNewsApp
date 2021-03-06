package com.java.zhangzhexin.model;

import java.util.Map;

public class ScholarCard extends BaseCard{
    public String avatar;
    public String name;
    public String position;
    public boolean passed;
    public Map<String, String> profile;
    public int idx;

    ScholarCard(String avatar, String name, String position, boolean passed, Map<String, String> profile) {
        this.avatar = avatar;
        this.name = name;
        this.position = position;
        this.passed = passed;
        this.profile = profile;
    }

    public void display() {
        System.out.println("avatar:" + avatar + "\nname:" + name + "\nposition:" + position + "\npassed:" + passed);
        for (String key : profile.keySet()) {
            System.out.println(key + ":" + profile.get(key));
        }
    }
}
