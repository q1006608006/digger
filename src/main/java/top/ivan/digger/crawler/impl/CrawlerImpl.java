package top.ivan.digger.crawler.impl;

import top.ivan.digger.crawler.*;
import top.ivan.digger.domain.DiggerResult;
import top.ivan.digger.domain.DiggerTask;
import top.ivan.digger.filter.DiggerFilter;

import java.io.IOException;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/15
 */
public class CrawlerImpl extends DiggerCrawler {

    protected CrawlerImpl(CrawlerContext context) {
        super(context);
    }

    private HttpLoader loader;

    @Override
    public void peek(DiggerTask task, CrawlerCallback callback) throws IOException {
        String body  = loader.getSource(task.getTarget());
        DiggerResult result = filterResult(body,task.getFilter());
        callback.callback(result);
    }

    private DiggerResult filterResult(String source, DiggerFilter filter) {
        return filter.doFilter(source);
    }

    public HttpLoader getLoader() {
        return loader;
    }

    public void setLoader(HttpLoader loader) {
        this.loader = loader;
    }
}
