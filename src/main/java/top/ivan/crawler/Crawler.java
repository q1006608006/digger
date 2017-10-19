package top.ivan.crawler;

import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/19
 */
public class Crawler {
    private HtmlLoader loader;
    private Filter filter;

    public Map<String,Object> parse(String url) {
        IXHtml html = loader.loaderHtml(url);
        return filter.doFilter(html);
    }
}
