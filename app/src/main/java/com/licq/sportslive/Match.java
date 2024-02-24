package com.licq.sportslive;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class Match {
    public Match(Element tr, LocalDateTime date) {
        Elements tds = tr.select("td");
        this.category = new Category(tds.get(0).attr("title"));
        this.subCategory = new SubCategory(tds.get(1).text());
        this.startTime = forStartTime(tds.get(2).text(), date);
        this.status = Status.from(tds.get(3).text());
        if(tds.get(4).hasAttr("colspan")){
            this.title = tds.get(4).text();
            this.links = new Links(tds.get(5));
        }else{
            this.title = generateTitle(tds.get(4).text(), tds.get(5).text(), tds.get(6).text());
            this.links = new Links(tds.get(7));
        }
    }

    public boolean canWatch(){
        return this.status == Status.IN_PROGRESS || (this.status == Status.NOT_STARTED && in5Minutes());
    }

    private boolean in5Minutes() {
        if (this.startTime == null) return false;
        return this.startTime.isBefore(LocalDateTime.now().plusMinutes(5));
    }

    private String generateTitle(String host, String vs, String guest) {
        return host + " vs " + guest;
    }

    private LocalDateTime forStartTime(String text, LocalDateTime date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime time = LocalTime.parse(text, formatter);
            return date.withHour(time.getHour()).withMinute(time.getMinute()).withSecond(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Category category;
    private SubCategory subCategory;
    private LocalDateTime startTime;
    private Status status;
    private String title;
    private Links links;

    public Category getCategory() {
        return this.category;
    }

    public SubCategory getSubCategory() {
        return this.subCategory;
    }

    public String getTitle() {
        return this.title;
    }

    public String getStartTimeForDisplay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return this.startTime.format(formatter);
    }

    public Status getStatus() {
        return status;
    }

    public Links getLinks() {
        return links;
    }
}
