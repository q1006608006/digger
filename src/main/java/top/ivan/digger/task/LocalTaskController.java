package top.ivan.digger.task;

import top.ivan.digger.domain.DiggerTask;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/28
 */
public class LocalTaskController implements DiggerTaskController {
    @Override
    public DiggerTask getTask() {
        return null;
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
