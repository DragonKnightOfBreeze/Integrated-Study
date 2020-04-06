package com.windea.study.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 两阶段终止模式的测试。
 */
public class TwoPhaseTerminationDemo {
    public static void main(String[] args) throws InterruptedException {
        var t = new TwoPhaseTermination();
        t.start();
        Thread.sleep(3500);
        t.stop();
    }
}

class TwoPhaseTermination {
    private static Logger logger = LoggerFactory.getLogger("TwoPhaseTermination");

    private Thread monitor;

    /** 启动监控线程。 */
    public void start() {
        monitor = new Thread(() -> {
            while(true) {
                Thread currentThread = Thread.currentThread();
                if(currentThread.isInterrupted()) {
                    //退出程序
                    logger.debug("程序被打断。");
                    break;
                }
                try {
                    Thread.sleep(2000);
                    logger.debug("执行监控记录……");
                } catch(InterruptedException e) {
                    e.printStackTrace();
                    //重新设置打断标记
                    currentThread.interrupt();
                }
            }
        });
        monitor.start();
    }

    /** 停止监控线程。 */
    public void stop() {
        monitor.interrupt();
    }
}
