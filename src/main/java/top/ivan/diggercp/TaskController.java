package top.ivan.diggercp;

import top.ivan.digger.domain.DiggerTask;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public interface TaskController {
    DiggerTask getTask();

    boolean completeTask(DiggerTask task);

    boolean undoTask(DiggerTask task);
}
