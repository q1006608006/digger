package top.ivan.digger.crawler.impl;

import top.ivan.digger.crawler.Crawler;
import top.ivan.digger.crawler.CrawlerCallback;
import top.ivan.digger.crawler.FilterRule;
import top.ivan.digger.domain.DiggerResult;
import top.ivan.digger.domain.DiggerTask;
import top.ivan.digger.util.CrawlerUtil;

import java.io.IOException;
import java.util.*;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/15
 */
public class CrawlerImpl implements Crawler {
    @Override
    public void peek(DiggerTask task, CrawlerCallback callback) {
        Map<String,FilterRule> ruleMap = task.getFilterRules();
        Iterator<Map.Entry<String,FilterRule>> ruleItor = ruleMap.entrySet().iterator();
        Map.Entry<String,FilterRule> entry;
        String body = null;
        try {
           body  = CrawlerUtil.request(task.getTarget()).response().getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DiggerResult result = new DiggerResult();
        while (ruleItor.hasNext()) {
            entry = ruleItor.next();
            List<String> retList = entry.getValue().filter(body);
            result.store(entry.getKey(),retList);
        }

        callback.callback(result);
    }

    public static void main(String[] args) throws IOException {

        for(int i = 0;i < 1;i ++ ) {
            Crawler crawler = new CrawlerImpl();
            DiggerTask task = new DiggerTask();
            task.setTarget("https://detail.tmall.com/item.htm?id=557759288520");
            Map<String,FilterRule> map = new HashMap<>();
            map.put("target", new FilterRule() {
                @Override
                public List<String> filter(String source) {
                    String value = source.replaceAll("[\\s\\S]*TShop\\.Setup\\(([\\s\\S]*?)\\);[\\s\\S]*","$1");
                    List<String> list = new ArrayList<>();
                    list.add(value);
                    return list;
                }
            });
            task.setFilterRules(map);
            crawler.peek(task,result -> {
                System.out.println(result.getStoreData());
            });
        }


    }
}
