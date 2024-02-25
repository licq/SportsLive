package com.licq.sportslive;

import androidx.annotation.NonNull;

import org.jsoup.nodes.Element;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Links implements Serializable, Iterable<Link> {

    private List<Link> links = new ArrayList<>();

    public Links(Element element) {
        element.select("a").forEach(a -> links.add(new Link(a.text(), a.attr("href"))));
    }

    public String description() {
        if (links != null)
            return links.size() + " 直播源";
        return "没有直播源";
    }


    @NonNull
    @Override
    public Iterator<Link> iterator() {
        return links.iterator();
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
