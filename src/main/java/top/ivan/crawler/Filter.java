package top.ivan.crawler;

import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/19
 */
public interface Filter {
    Map<String,Object> doFilter(IXHtml html);
}
