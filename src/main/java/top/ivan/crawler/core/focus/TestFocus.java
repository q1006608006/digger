package top.ivan.crawler.core.focus;

import top.ivan.crawler.Focus;
import top.ivan.crawler.FocusManager;
import top.ivan.crawler.FocusManagerHandle;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/25
 */
public class TestFocus implements Focus,FocusManagerHandle {
    @Override
    public String peek(String src, String target, String key) throws Exception {
        return null;
    }

    @Override
    public void setManager(FocusManager manager) {

    }

    public static boolean test(String src,String target) {


        return false;
    }
}
