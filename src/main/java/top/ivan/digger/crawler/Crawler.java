package top.ivan.digger.crawler;

import top.ivan.digger.task.DiggerTask;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public interface Crawler {
    void peek(DiggerTask task,CrawlerCallback callback);
}
