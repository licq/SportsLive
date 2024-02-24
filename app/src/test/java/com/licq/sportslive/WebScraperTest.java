package com.licq.sportslive;

import static org.junit.jupiter.api.Assertions.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class WebScraperTest {

    @Test
    void getStreamLink() {
        Document doc;
        try {
            doc = Jsoup.connect("http://www.lanqiuzhi.live/tv/plu-40561361.html").get();
            Elements media = doc.select(".media");
            media.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertNotNull(doc);
    }
}