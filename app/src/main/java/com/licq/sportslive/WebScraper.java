package com.licq.sportslive;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class WebScraper {

    private static String regex = "id=(.+?)\"";
    private static Pattern pattern = Pattern.compile(regex);
    public static Matches scrape() {
        Matches matches = new Matches();
        for (int i = 0; i < 3; ) {
            try {
                Document doc = Jsoup.connect("http://www.wuchajian.live").get();
                LocalDateTime date = null;

                for (Element tr : doc.select("tr")) {
                    if (tr.hasClass("date")) {
                        if (date == null) {
                            date = LocalDateTime.now();
                        } else {
                            date = LocalDateTime.now().plusDays(1);
                        }
                    }
                    if (tr.hasClass("against")) {
                        matches.addMatch(new Match(tr, date));
                    }
                }

                return matches;
            } catch (IOException e) {
                e.printStackTrace();
                i++;
            }
        }
        return matches;
    }

    public static String getStreamLink(Links links) {
        for (Link link : links) {
            Document doc = null;
            try {
                doc = Jsoup.connect(link.getUrl()).get();
                String iframeSrc = doc.select("div.media > iframe").get(0).attr("src");
                doc = Jsoup.connect(iframeSrc).referrer(getReferer(link.getUrl())).get();
                return videoUrl(doc.html());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private static String videoUrl(String pageContent){
        Matcher matcher = pattern.matcher(pageContent);
        if(matcher.find()){
            return matcher.group(1);
        }
        throw new RuntimeException("No video url found");
    }

    private static String getReferer(String url){
        String[] parts = url.split("/");
        return parts[0] + "//" + parts[2];
    }
}

