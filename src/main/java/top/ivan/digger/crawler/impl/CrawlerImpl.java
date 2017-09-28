package top.ivan.digger.crawler.impl;

import top.ivan.digger.crawler.Crawler;
import top.ivan.digger.crawler.CrawlerCallback;
import top.ivan.digger.crawler.interceptor.CrawlerSourceInterceptor;
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
public class CrawlerImpl implements Crawler {
    private CrawlerSourceInterceptor sourceInterceptor;

    private String getSource(String url) throws IOException {
        return sourceInterceptor.getSource(url);
    }
    @Override
    public void peek(DiggerTask task, CrawlerCallback callback) throws IOException {
        String body  = getSource(task.getTarget());
        DiggerResult result = filterResult(body,task.getFilter());
        callback.callback(result);
    }

    private DiggerResult filterResult(String source, DiggerFilter filter) {
        return filter.doFilter(source);
    }

    public CrawlerSourceInterceptor getSourceInterceptor() {
        return sourceInterceptor;
    }

    public void setSourceInterceptor(CrawlerSourceInterceptor sourceInterceptor) {
        this.sourceInterceptor = sourceInterceptor;
    }
}
