package top.ivan.crawler;

import org.jsoup.Jsoup;

import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public interface Focus {
    String peek(String src, String target, String key, Map<String, Object> tempMap);
}
