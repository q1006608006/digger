package top.ivan.digger.task;

import top.ivan.digger.Digger;
import top.ivan.digger.domain.DiggerTask;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public interface DiggerTaskController {
    DiggerTask getTask();
    boolean completeTask(DiggerTask task);
    boolean undoTask(DiggerTask task);
}
