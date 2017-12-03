package top.ivan.crawler;

import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public interface Crawler {
    Map<String,Object> parse(String url);
}
