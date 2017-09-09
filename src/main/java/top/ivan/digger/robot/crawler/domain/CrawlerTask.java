package top.ivan.digger.robot.crawler.domain;

import java.util.Queue;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/9
 */
public class CrawlerTask {

    private Queue<String> targetUrlQueue;

    public Queue<String> getTargetUrlQueue() {
        return targetUrlQueue;
    }

    public void setTargetUrlQueue(Queue<String> targetUrlQueue) {
        this.targetUrlQueue = targetUrlQueue;
    }
}
