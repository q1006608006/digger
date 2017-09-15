package top.ivan.digger.crawler;

import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/14
 */
public interface FilterRule {
    List<String> filter(String source);
}
