package top.ivan.digger;

import top.badtheway.sa.core.container.FileSystemBeanContext;
import top.ivan.digger.crawler.Crawler;
import top.ivan.digger.crawler.CrawlerFactory;
import top.ivan.digger.crawler.DiggerCrawler;
import top.ivan.digger.crawler.impl.DefaultCrawlerFactory;
import top.ivan.digger.domain.DiggerTask;
import top.ivan.digger.task.DiggerTaskController;
import top.ivan.digger.task.LocalTaskController;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public class Digger {

    private String id;
    //依赖项
    private DiggerTaskController taskController;
    private CrawlerFactory crawlerFactory;
    private ThreadPoolExecutor threadExecutor;

    //配置项
    private int maxIdle = 20;
    private int maxWait = 5000;

    //容器
    private Queue<DiggerCrawler> crawlerQueue;

    private boolean run;

    private void init() {
        if(null == crawlerQueue) {
            crawlerQueue = new LinkedBlockingDeque<>(maxIdle);
        }
        if(null == crawlerFactory) {
            crawlerFactory = new DefaultCrawlerFactory();
        }
        if(null == threadExecutor) {
            threadExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        }
        if(null == taskController) {
            taskController = new LocalTaskController();
        }
    }


    public void digger() {

        while (run) {
            threadExecutor.execute(() -> {
                //it can replace by a singleton
                DiggerTask task = taskController.getTask();
                try {
                    DiggerCrawler crawler = crawlerQueue.poll();
                    crawler.peek(task, result -> {
                        taskController.completeTask(task);
                        crawlerQueue.add(crawler);
                        // TODO: 2017/9/11  save result
                        //save(result)
                    });
                } catch (Exception e) {
                    taskController.undoTask(task);
                }
            });
        }
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DiggerTaskController getTaskController() {
        return taskController;
    }

    public void setTaskController(DiggerTaskController taskController) {
        this.taskController = taskController;
    }

    public ThreadPoolExecutor getThreadExecutor() {
        return threadExecutor;
    }

    public void setThreadExecutor(ThreadPoolExecutor threadExecutor) {
        this.threadExecutor = threadExecutor;
    }

    public void setCrawlerFactory(CrawlerFactory factory) {
        this.crawlerFactory = factory;
    }

    public CrawlerFactory getCrawlerFactory() {
        return this.crawlerFactory;
    }

    protected void setCrawlerQueue(Queue<DiggerCrawler> crawlerQueue) {
        this.crawlerQueue = crawlerQueue;
    }

    public Queue<DiggerCrawler> getCrawlerQueue() {
        return crawlerQueue;
    }

}
