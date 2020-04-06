package com.windea.study.concurrent.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ThreadStateTest {
    private static Logger logger = LoggerFactory.getLogger("ThreadStateTest");

    @Test
    public void test1() throws IOException {
        //NEW
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                logger.debug("running...");
            }
        };

        //RUNNABLE
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while(true) { // runnable
                }
            }
        };
        t2.start();

        //TERMINATED
        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                logger.debug("running...");
            }
        };
        t3.start();

        //BLOCKED
        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                synchronized(ThreadStateTest.class) {
                    try {
                        Thread.sleep(1000000); // timed_waiting
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t4.start();

        //WAITING
        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                try {
                    t2.join(); // waiting
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t5.start();

        //TIMED_WAITING
        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                synchronized(ThreadStateTest.class) { // blocked
                    try {
                        Thread.sleep(1000000);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t6.start();

        try {
            Thread.sleep(500);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        logger.debug("t1 state {}", t1.getState());
        logger.debug("t2 state {}", t2.getState());
        logger.debug("t3 state {}", t3.getState());
        logger.debug("t4 state {}", t4.getState());
        logger.debug("t5 state {}", t5.getState());
        logger.debug("t6 state {}", t6.getState());
    }
}
