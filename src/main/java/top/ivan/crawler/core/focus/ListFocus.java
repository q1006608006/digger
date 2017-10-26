package top.ivan.crawler.core.focus;

import top.ivan.crawler.Focus;
import top.ivan.crawler.FocusManager;
import top.ivan.crawler.FocusManagerHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/23
 */
public class ListFocus implements Focus, FocusManagerHandle {
    private FocusManager manager;

    public static final String MODULE_PART = "\\[([\\s\\S]*?)\\]";
    public static final String MODULE_VALUE_RP = "[\\S\\s]*" + MODULE_PART + "[\\S\\s]*";
    public static final String MODULE_VALUE_KEY = "$1";
    public static final String TEST_MODULE = "^test" + MODULE_PART + "$";
    public static final String NULL_MOUDLE = "^null" + MODULE_PART + "$";

    @Override
    public String peek(String src, String target, String key) throws Exception {
        List list = JsonFocus.fromJson(src, List.class);
        List ret = new ArrayList();
        KeyInvoker invoker;
        String testKey = target.replaceAll(MODULE_VALUE_RP,MODULE_VALUE_KEY);
        String tempValue;
        Focus focus;

        foreach(list, o -> {
            if (TEST_MODULE.matches(o.toString())) {
                String value;
                if(TestFocus.test(o.toString(),target)) {
                    try {
                        value = manager.getFocus("test").peek(o.toString(),testKey,key);
                    } catch (Exception e) {
                        value = null;
                    }
                } else if(NULL_MOUDLE.matches(o.toString())) {

                }
            }
        });
        return JsonFocus.toJson(ret);
    }


    public static void foreach(Object[] objs, KeyInvoker invoker) {
        for (Object o : objs) {
            invoker.invoke(o);
        }
    }

    public static void foreach(List list, KeyInvoker invoker) {
        foreach(list.toArray(), invoker);
    }

    public static String nullValue(String src) {
        if (null == src || "".equals(src.trim())) {
            return null;
        }
        return src;
    }

    @Override
    public void setManager(FocusManager manager) {
        this.manager = manager;
    }

    interface KeyInvoker {
        void invoke(Object obj);
    }

}
