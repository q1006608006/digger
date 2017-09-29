package top.ivan.digger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.ivan.digger.crawler.CrawlerFactory;
import top.ivan.digger.crawler.DiggerCrawler;
import top.ivan.digger.crawler.impl.DefaultCrawlerFactory;
import top.ivan.digger.domain.DiggerResult;
import top.ivan.digger.domain.DiggerTask;
import top.ivan.digger.task.DiggerTaskController;
import top.ivan.digger.task.LocalTaskController;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/11
 */
public class Digger {
    private static Logger logger = LogManager.getLogger(Digger.class);

    private String id;
    //依赖项
    private DiggerTaskController taskController;
    private CrawlerFactory crawlerFactory;

    //配置项
    private int maxIdle = 20;
    private int maxWait = 5000;

    //容器
    private BlockingQueue<DiggerCrawler> crawlerQueue;

    private BlockingQueue<DiggerResult> resultQueue;
    private ThreadPoolExecutor threadExecutor;

    private void init() {
        if (null == crawlerQueue) {
            crawlerQueue = new LinkedBlockingQueue<>(maxIdle);
        }
        if (null == resultQueue) {
            resultQueue = new LinkedBlockingQueue<>();
        }
        if (null == crawlerFactory) {
            crawlerFactory = new DefaultCrawlerFactory();
        }

        if (null == threadExecutor) {
            threadExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
            if (null == taskController) {
                taskController = new LocalTaskController();
            }
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

    protected void setCrawlerQueue(BlockingQueue<DiggerCrawler> crawlerQueue) {
        this.crawlerQueue = crawlerQueue;
    }

    public BlockingQueue<DiggerCrawler> getCrawlerQueue() {
        return crawlerQueue;
    }

    private boolean isRun = false;

    public void digger() {
        init();
        isRun = true;
        resultKeeper();
        while (isRun) {
            DiggerCrawler crawler;
            DiggerTask task;
            task = taskController.getTask();
            crawler = takeCrawler();
            threadExecutor.execute(() -> {
                try {
                    crawler.peek(task, result -> resultQueue.add(result));
                    taskController.completeTask(task);
                } catch (InterruptedException e) {
                    logger.error("blocking queue error:{}", e.getMessage());
                } catch (Exception e) {
                    taskController.undoTask(task);
                } finally {
                    crawlerQueue.add(crawler);
                }
            });
        }
    }

    private void resultKeeper() {
        threadExecutor.execute(() -> {
            while (isRun || resultQueue.size() > 0) {
                try {
                    DiggerResult result = resultQueue.take();
                    System.out.println(result.getStoreData());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private int crawlerCount = 0;

    private DiggerCrawler takeCrawler() {
        if (crawlerQueue.size() > 0) {
            return crawlerQueue.poll();
        }
        if (crawlerCount >= maxIdle) {
            try {
                DiggerCrawler crawler = crawlerQueue.take();
                return crawler;
            } catch (InterruptedException e) {
                logger.error("blocking queue error:{}", e.getMessage());
            }
        }
        crawlerCount++;
        return crawlerFactory.newCrawler(null);
    }

}
