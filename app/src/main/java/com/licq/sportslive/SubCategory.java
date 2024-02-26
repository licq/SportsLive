package com.licq.sportslive;

import java.util.HashSet;
import java.util.Set;

public class SubCategory {
    private static final Set<String> whiteList = new HashSet<>();

    static {
        whiteList.add("英超");
        whiteList.add("西甲");
        whiteList.add("意甲");
        whiteList.add("德甲");
        whiteList.add("法甲");
        whiteList.add("NBA");
        whiteList.add("中超");
        whiteList.add("欧洲杯");
        whiteList.add("世界杯");
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
        return whiteList.contains(text);
    }
}
