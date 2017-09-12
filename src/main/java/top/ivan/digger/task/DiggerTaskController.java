package top.ivan.digger.task;

import top.ivan.digger.Digger;
import top.ivan.digger.domain.DiggerResult;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public interface DiggerTaskController {
    DiggerTask getTask(String id);
    String register(Digger digger);
    boolean completeTask(DiggerTask task);
    boolean undoTask(DiggerTask task);
}
