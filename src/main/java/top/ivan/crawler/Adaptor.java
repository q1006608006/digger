package top.ivan.crawler;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/20
 */
public interface Adaptor<F,T> {
    T convert(F f,Class<? extends T> clazz);
}
