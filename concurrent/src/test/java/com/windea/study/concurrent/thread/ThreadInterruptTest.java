package com.windea.study.concurrent.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.LockSupport;

//只是告诉该线程需要被打断，真正的打断操作需要该线程自己执行
public class ThreadInterruptTest {
    private static Logger logger = LoggerFactory.getLogger("ThreadInterruptTest");

    //打断wait、sleep、join的线程，会清空打断状态
    @Test
    public void test1() throws InterruptedException {
        var t1 = new Thread(() -> {
            logger.debug("sleep...");
            try {
                Thread.sleep(5000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        Thread.sleep(1000);
        logger.debug("interrupt");
        t1.interrupt();
        logger.debug("打断状态：{}", t1.isInterrupted());

        t1.join();
    }

    //打断正常运行的线程，不会清空打断状态
    @Test
    public void test2() throws InterruptedException {
        var t1 = new Thread(() -> {
            while(true) {
                var current = Thread.currentThread();
                var interrupted = current.isInterrupted();
                if(interrupted) {
                    logger.debug("打断状态：{}", interrupted);
                    break;
                }
            }
        });
        t1.start();
        Thread.sleep(500);
        t1.interrupt();

        t1.join();
    }

    //打断park线程，不会清空打断状态
    //如果打断标记已经是true，则park会失效
    @Test
    public void test3() throws InterruptedException {
        var t1 = new Thread(() -> {
            logger.debug("park...");
            LockSupport.park();
            //下面的方法实际上是不会执行的，除非被打断
            logger.debug("unpark by interrupted...");
            logger.debug("打断状态：{}", Thread.currentThread().isInterrupted());

            logger.debug("try park..");
            LockSupport.park();
        });
        t1.start();
        Thread.sleep(1000);
        t1.interrupt();

        t1.join();
    }
}
