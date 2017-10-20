package top.ivan.crawler.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import top.ivan.crawler.Adaptor;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public class DefaultAdaptor<F,T> implements Adaptor<F,T> {
    private static Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    @Override
    public T convert(F o,Class<? extends T> clazz) {
        return GSON.fromJson(GSON.toJson(o),clazz);
    }
}
