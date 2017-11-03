package top.ivan.crawler.utils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public class ClassBuilder {
    public static <T> T build(Class<T> clazz) throws Exception {
        return clazz.newInstance();
    }

}
