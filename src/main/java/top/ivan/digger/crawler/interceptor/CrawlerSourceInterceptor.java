package top.ivan.digger.crawler.interceptor;

import java.io.IOException;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/22
 */
public interface CrawlerSourceInterceptor {
    String getSource(String url) throws IOException;
}
