package top.ivan.crawler.core.focus;

import top.ivan.crawler.Examiner;
import top.ivan.crawler.Focus;
import top.ivan.crawler.FocusManager;
import top.ivan.crawler.ExportFocusHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/23
 */
public class ListExportFocus implements Focus, ExportFocusHandle {
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
                result.add(mod.peek(TestExportFocus.notNullValue(o), target, key));
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
                case "$"://null -> null
                    result.add(o);
                    break;
                case "#"://null -> ""
                    result.add(TestExportFocus.notNullValue(o));
                    break;
                case "+"://null -> "null"
                    result.add(null == o ? "null" : o);
                    break;
                case "-"://null -> skip
                    if(null != o) {
                        result.add(o);
                    }
                    break;
                default:
                    result.add(-1);
                    break;
            }
        });
        return result;
    }

    private String getTestNullValue(Object obj, boolean openNullValue) {
        if(openNullValue) {
            return TestExportFocus.nullValue(obj);
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


    public static void main(String[] args) throws Exception {
        String listStr = "['',1,2,a]";
//        System.out.println(listStr.replaceAll("a",""));
//        System.out.println(listStr.toString());
        System.out.println(new ListExportFocus().peek(listStr,"test[regex[a=bsx]]]","A=B()"));
    }

}
