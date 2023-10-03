package utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Utils {

    public String getTextFromHTML(String HTML) {
        Document document = Jsoup.parse(HTML, "UTF-8");
        Element element = document.body().select("ul").first();
        if (element != null) {
            return element.text();
        } else {
            return "";
        }

    }
}
