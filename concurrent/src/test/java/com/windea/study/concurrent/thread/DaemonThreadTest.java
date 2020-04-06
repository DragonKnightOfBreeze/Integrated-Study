package com.windea.study.concurrent.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaemonThreadTest {
    private static Logger logger = LoggerFactory.getLogger("DaemonThreadTest");

    //默认情况下，java进行需要等待所有线程都执行结束，才会结束
    //但只要其他非守护进程结束了，即使守护进程的代码没有执行结束，也会强制结束
    @Test
    public void test1() throws InterruptedException {
        logger.debug("开始运行……");
        var t1 = new Thread(() -> {
            logger.debug("开始运行……");
            try {
                Thread.sleep(2000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            logger.debug("运行结束……");
        }, "daemon");
        t1.setDaemon(true);
        t1.start();

        Thread.sleep(1000);
        logger.debug("运行结束……");
    }
}
