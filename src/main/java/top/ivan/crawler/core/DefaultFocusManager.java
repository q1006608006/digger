package top.ivan.crawler.core;

import top.ivan.crawler.Focus;
import top.ivan.crawler.FocusManager;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/10/31
 */
public class DefaultFocusManager implements FocusManager {
    private Map<String,Focus> focusMap = new HashMap<>();
    @Override
    public Focus getFocus(String type) {
        return focusMap.get(type);
    }

    public void setFocus(String type,Focus focus) {
        focusMap.put(type,focus);
    }

    public Focus removeFocus(String type) {
        return focusMap.remove(type);
    }
}
