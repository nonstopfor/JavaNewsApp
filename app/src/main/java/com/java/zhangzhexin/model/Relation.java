package com.java.zhangzhexin.model;

public class Relation {
    public String relation;
    public String label;
    public boolean forward;

    public Relation(String relation, String label, boolean forward) {
        this.relation = relation;
        this.label = label;
        this.forward = forward;
    }

    public void display(){
        System.out.println("relation:"+relation+"\nlabel:"+label+"\nforward:"+forward);
    }
}
