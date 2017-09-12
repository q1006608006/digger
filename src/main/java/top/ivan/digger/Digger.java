package top.ivan.digger;

import top.badtheway.sa.core.container.FileSystemBeanContext;
import top.ivan.digger.crawler.Crawler;
import top.ivan.digger.task.DiggerTask;
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
            synchronized (this) {
                threadExecutor.execute(() -> {
                    //it can replace by a singleton
                    DiggerTask task = taskController.getTask(this.id);
                    try {
                        crawler.peek(task, result -> {
                            taskController.completeTask(task);
                            // TODO: 2017/9/11  save result
                            //save(result)
                            synchronized (Digger.this) {
                                Digger.this.notify();
                            }
                        });
                    } catch (Exception e) {
                        taskController.undoTask(task);
                    } finally {
                        Digger.this.notify();
                    }
                });
                this.wait();
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        synchronized (Digger.class) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    synchronized (Digger.class) {
                        Digger.class.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            Digger.class.wait();
        }


        System.out.println("end");
    }
}
