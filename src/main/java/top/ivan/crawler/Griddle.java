package top.ivan.crawler;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public interface Griddle {
    Map<String,String> doFilter(String src,Map<String,String> keyMap);
}
