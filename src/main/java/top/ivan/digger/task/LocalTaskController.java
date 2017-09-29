package top.ivan.digger.task;

import org.jsoup.Jsoup;
import top.ivan.digger.domain.DiggerResult;
import top.ivan.digger.domain.DiggerTask;
import top.ivan.digger.filter.DiggerFilter;

import java.util.UUID;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/28
 */
public class LocalTaskController implements DiggerTaskController {
    @Override
    public DiggerTask getTask() {
        DiggerTask task = new DiggerTask(UUID.randomUUID().toString());
        task.setTarget("http://www.bilibili.com");
        task.setFilter(new DiggerFilter() {
            @Override
            public DiggerResult doFilter(String src) {
                String str = Jsoup.parse(src).select("a").first().attr("href");
                DiggerResult result = new DiggerResult();
                result.store("target",str);
                return result;
            }
        });
        return task;
    }

    @Override
    public boolean completeTask(DiggerTask task) {
        return false;
    }

    @Override
    public boolean undoTask(DiggerTask task) {
        return false;
    }
}
