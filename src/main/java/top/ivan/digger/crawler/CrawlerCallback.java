package top.ivan.digger.crawler;


import top.ivan.digger.domain.DiggerResult;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public interface CrawlerCallback {
    void callback(DiggerResult result);
}
