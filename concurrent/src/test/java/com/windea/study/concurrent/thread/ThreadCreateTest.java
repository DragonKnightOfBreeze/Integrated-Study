package com.windea.study.concurrent.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class ThreadCreateTest {
    private static Logger logger = LoggerFactory.getLogger("ThreadCreateTest");

    @Test
    public void test1() throws InterruptedException {
        var t1 = new Thread() {
            @Override
            public void run() {
                logger.debug("running");
            }
        };
        t1.setName("t1");
        t1.start();
        t1.join();

        logger.debug("running");
    }

    @Test
    public void test2() throws InterruptedException {
        var t1 = new Thread(() -> {
            logger.debug("running");
        }, "t1");
        t1.start();
        t1.join();

        logger.debug("running");
    }

    @Test
    public void test3() throws InterruptedException, ExecutionException {
        var task1 = new FutureTask<>(() -> {
            logger.debug("running");
            return "finished";
        });
        var t1 = new Thread(task1, "t1");
        t1.start();
        t1.join();

        var result = task1.get();
        logger.debug("result: {}", result);


        logger.debug("running");
    }
}
