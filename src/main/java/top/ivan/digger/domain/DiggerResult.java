package top.ivan.digger.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public class DiggerResult {
    private Map<String,List<String>> storeData = new HashMap<>();

    public void store(String key,List<String> data) {
        storeData.put(key,data);
    }

    public Map<String,List<String>> getStoreData() {
        return storeData;
    }
}
