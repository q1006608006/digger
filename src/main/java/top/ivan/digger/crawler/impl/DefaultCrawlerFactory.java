package top.ivan.digger.crawler.impl;

import org.jsoup.Jsoup;
import top.ivan.digger.crawler.CrawlerCallback;
import top.ivan.digger.crawler.CrawlerContext;
import top.ivan.digger.crawler.CrawlerFactory;
import top.ivan.digger.crawler.DiggerCrawler;
import top.ivan.digger.domain.DiggerTask;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/28
 */
public class DefaultCrawlerFactory implements CrawlerFactory {
    @Override
    public DiggerCrawler newCrawler(CrawlerContext context) {
        return new DiggerCrawler(context) {
            @Override
            public void peek(DiggerTask task, CrawlerCallback callback) throws Exception {
                callback.callback(task.getFilter().doFilter(Jsoup.connect(task.getTarget()).get().outerHtml()));
            }
        };
    }
}
