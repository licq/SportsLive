package com.licq.sportslive;

import org.jsoup.nodes.Element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Links implements Serializable {

    private List<Link> links = new ArrayList<>();

    public Links(Element element) {
        element.select("a").forEach(a -> links.add(new Link(a.text(), a.attr("href"))));
    }

    public String description() {
        if (links != null)
            return links.size() + " 直播源";
        return "没有直播源";
    }

    public Link first() {
        if(links != null){
            return links.get(0);
        }
        return null;
    }
}

class Link implements Serializable {
    private String text;
    private String url;

    public Link(String text, String url) {
        this.text = text;
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public String toString() {
        return text;
    }
}
