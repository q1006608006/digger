package top.ivan.diggercp;

import top.ivan.digger.domain.DiggerResult;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/26
 */
public interface Filter {
    DiggerResult doFilter(String src);
}
