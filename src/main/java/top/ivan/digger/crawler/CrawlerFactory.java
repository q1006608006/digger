package top.ivan.digger.crawler;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/28
 */
public interface CrawlerFactory {
    DiggerCrawler newCrawler(CrawlerContext context);
}
