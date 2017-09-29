package top.ivan.digger.crawler;

import java.util.HashMap;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/28
 */
public class CrawlerContext {
    private HashMap<String,String> config = new HashMap<>();

    public void setProperty(String key,String value) {
        config.put(key,value);
    }

    public String getProperty(String attr) {
        return config.get(attr);
    }
}
