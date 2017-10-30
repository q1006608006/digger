package top.ivan.crawler.core.focus;

import top.ivan.crawler.*;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/23
 */
public class ListFocus implements Focus, ExportFocusHandle {
    private FocusManager manager;

    @Override
    public String peek(String src, String target, String key) throws Exception {
        List list = JsonFocus.fromJson(src, List.class);
        if (ExportFocusHandle.isExportTarget(target)) {
            Focus focus = ExportFocusHandle.getExportFocus(target,manager);
            target = ExportFocusHandle.getExportTarget(target);
            return JsonFocus.toJson(parseExport(list, focus, target, key));
        } else {
            return JsonFocus.toJson(parseNull(list, target));
        }
    }

    private List parseExport(List src, Focus mod, String target, String key) {
        List result = new ArrayList();
        foreach(src, o -> {
            try {
                result.add(mod.peek(TestFocus.notNullValue(o), target, key));
            } catch (Exception e) {
                result.add(Examiner.exceptionMessage(this.getClass(),e));
            }
        });
        return result;
    }


    private List parseNull(List src, String type) {
        List result = new ArrayList();
        boolean openNullValue = false;
        if (type.startsWith("@")) {
            type = type.replace("@", "");
            openNullValue = true;
        }
        boolean finalOpenNullValue = openNullValue;
        String finalType = type;
        foreach(src, o -> {
            o = getTestNullValue(o, finalOpenNullValue);
            switch (finalType) {
                case "$"://"" -> null
                    result.add(o);
                    break;
                case "#"://null -> ""
                    result.add(TestFocus.notNullValue(o));
                    break;
                case "+"://null -> "null" >> ""->"null"
                    result.add(null == o ? "null" : o);
                    break;
                case "-"://null -> skip  >> "" -> skip
                    if(null != o) {
                        result.add(o);
                    }
                    break;
                default:
                    result.add(Examiner.exceptionMessage(ListFocus.class,new UnSupportFocusException("not suitable order found")));
                    break;
            }
        });
        return result;
    }

    private String getTestNullValue(Object obj, boolean openNullValue) {
        if(openNullValue) {
            return TestFocus.nullValue(obj);
        }
        return null == obj ? null : obj.toString();
    }


    public static void foreach(Object[] objs, KeyInvoker invoker) {
        for (Object o : objs) {
            invoker.invoke(o);
        }
    }

    public static void foreach(List list, KeyInvoker invoker) {
        foreach(list.toArray(), invoker);
    }

    @Override
    public void setManager(FocusManager manager) {
        this.manager = manager;
    }

    interface KeyInvoker {
        void invoke(Object obj);
    }

}
