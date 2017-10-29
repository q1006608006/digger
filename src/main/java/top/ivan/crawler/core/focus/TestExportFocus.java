package top.ivan.crawler.core.focus;

import top.ivan.crawler.Focus;
import top.ivan.crawler.FocusManager;
import top.ivan.crawler.ExportFocusHandle;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/25
 */
public class TestExportFocus implements Focus,ExportFocusHandle {

    private FocusManager manager;
    @Override
    public String peek(String src, String target, String key) throws Exception {
//        String moduleType =
        return null;
    }

    @Override
    public void setManager(FocusManager manager) {
        this.manager = manager;
    }

    public static String nullValue(Object src) {
        if (null == src || "".equals(src.toString().trim())) {
            return null;
        }
        return src.toString();
    }
    public static String notNullValue(Object src) {

        if(null == src) {
            return "";
        } else {
            return src.toString();
        }
    }
}
