package top.ivan.digger.filter;

import top.ivan.digger.domain.DiggerResult;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/26
 */
public interface DiggerFilter {
    DiggerResult doFilter(String src);
}
