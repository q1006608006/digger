package top.ivan.digger;

import top.badtheway.sa.core.container.FileSystemBeanContext;
import top.ivan.digger.crawler.Crawler;
import top.ivan.digger.domain.DiggerTask;
import top.ivan.digger.task.DiggerTaskController;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public class Digger {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private DiggerTaskController taskController;
    private Crawler crawler;
    private ThreadPoolExecutor threadExecutor;

    private FileSystemBeanContext context;

    private boolean run;


    public void digger() throws InterruptedException {
        while (run) {
            threadExecutor.execute(() -> {
                //it can replace by a singleton
                DiggerTask task = taskController.getTask();
                try {
                    crawler.peek(task, result -> {
                        taskController.completeTask(task);
                        // TODO: 2017/9/11  save result
                        //save(result)
                    });
                } catch (Exception e) {
                    taskController.undoTask(task);
                }
            });
        }
    }

}
