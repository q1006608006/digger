package top.ivan.digger.robot.crawler;

import top.ivan.digger.robot.Robot;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/8
 */
public interface Crawler {
    void startCatch(String url,CrawlerCallback callback);
}
