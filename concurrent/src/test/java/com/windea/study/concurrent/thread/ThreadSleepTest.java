package com.windea.study.concurrent.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadSleepTest {
    private static Logger logger = LoggerFactory.getLogger("ThreadSleepTest");

    @Test
    public void test1() throws InterruptedException {
        var t1 = new Thread(() -> {
            logger.debug("start sleep...");
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                logger.debug("wake up.");
                e.printStackTrace();
            }
            logger.debug("end sleep...");
        });
        t1.start();

        Thread.sleep(500);
        logger.debug("interrupt...");
        t1.interrupt();
    }
}
