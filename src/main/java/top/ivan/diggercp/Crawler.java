package top.ivan.diggercp;

import top.ivan.digger.crawler.CrawlerCallback;
import top.ivan.digger.domain.DiggerTask;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public interface Crawler {
    void peek(DiggerTask task, CrawlerCallback callback) throws Exception;
}
