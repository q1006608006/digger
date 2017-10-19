package top.ivan.diggercp;

import java.util.HashMap;
import java.util.Map;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public class Result {
    private Map<String,String> storeData = new HashMap<>();

    public void store(String target,String data) {
        storeData.put(target,data);
    }

    public Map<String,String> getStoreData() {
        return storeData;
    }
}
