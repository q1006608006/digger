package top.ivan.digger.crawler;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/28
 */
public abstract class DiggerCrawler implements Crawler {
    private CrawlerContext context;
    public DiggerCrawler(CrawlerContext context){
        this.context = context;
    }
    public CrawlerContext getContext() {
         return context;
    }
}
