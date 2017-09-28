package top.ivan.digger;

import org.eclipse.jetty.util.BlockingArrayQueue;
import top.ivan.digger.crawler.CrawlerFactory;
import top.ivan.digger.task.DiggerTaskController;
import top.ivan.digger.task.LocalTaskController;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * description
 *
 * @author Administrator
 * @date 2017/9/28
 */
public class DiggerMan {
    public static void main(String[] args) throws InterruptedException {
/*        Digger digger = new Digger();

        digger.digger();*/
        BlockingQueue queue = new LinkedBlockingQueue(1);
        queue.add("Stri21ng");
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
//                    Thread.sleep(1000);
                    queue.put("String1");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        Thread.sleep(3000);
        System.out.println(queue.take());
        System.out.println(queue.size());
    }
}
