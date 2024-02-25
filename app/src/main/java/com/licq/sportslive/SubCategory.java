package com.licq.sportslive;

import java.util.HashSet;
import java.util.Set;

public class SubCategory {

    private static final Set<String> blackList;

    static {
        blackList = new HashSet<>();
        blackList.add("哥斯甲");
        blackList.add("美联");
        blackList.add("阿甲");
        blackList.add("墨联");
        blackList.add("哥伦甲");
        blackList.add("日职乙");
        blackList.add("J联赛");
        blackList.add("NCAA");
        blackList.add("澳超");
        blackList.add("香港超");
        blackList.add("泰超");
        blackList.add("土超");
        blackList.add("乌超");
        blackList.add("波兰超");
        blackList.add("苏超");
        blackList.add("罗甲");
        blackList.add("德乙");
        blackList.add("比甲");
        blackList.add("保超");
        blackList.add("丹超");
        blackList.add("乌拉甲");
        blackList.add("西乙");
        blackList.add("葡甲");
        blackList.add("葡超");
        blackList.add("瑞士超");
        blackList.add("奧甲");
        blackList.add("阿联酋超");
        blackList.add("沙特联");
        blackList.add("印超");
        blackList.add("克罗甲");
        blackList.add("智甲");
        blackList.add("意乙");
        blackList.add("荷甲");
        blackList.add("篮球");
        blackList.add("男篮非洲杯");
        blackList.add("男篮世欧预");
        blackList.add("WCBA");
        blackList.add("男篮欧国杯");
        blackList.add("埃及超");
        blackList.add("希超");
    }

    private final String text;

    public SubCategory(String text) {

        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public boolean wantToWatch() {
        return !blackList.contains(text);
    }
}
