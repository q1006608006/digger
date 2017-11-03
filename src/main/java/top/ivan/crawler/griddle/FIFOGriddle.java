package top.ivan.crawler.griddle;

import top.ivan.crawler.Griddle;

import java.util.*;

/**
 * description
 *
 * @author Administrator
 * @date 2017/11/1
 */
public class FIFOGriddle implements Griddle {
    List<GriddleAisle> aisleList;

    public FIFOGriddle(List<GriddleAisle> aisleList) {
        this.aisleList = aisleList;
    }

    public FIFOGriddle(GriddleAisle aisle) {
        aisleList = new LinkedList<>();
        aisleList.add(aisle);
    }
    @Override
    public Map<String, String> doFilter(String src) {
        Map<String,String> retMap = new HashMap<>();
        GriddleAisle aisle;
        for(int i=0;i<aisleList.size();i++) {
            aisle = aisleList.get(i);
            try {
                retMap.put(aisle.getPeek(),aisle.pass(src,retMap));
            } catch (Exception e) {
                retMap.put(aisle.getPeek(),aisle.getDefaultValue());
            }
        }
        return retMap;
    }

    public void setAisles(List<GriddleAisle> aisleList) {
        this.aisleList = aisleList;
    }

}
