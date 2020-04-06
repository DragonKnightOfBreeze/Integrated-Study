package com.windea.study.interview.concurrent.pcp;

//使用信号量Semaphore解决生产者/消费者问题。

//Semaphore是一种基于计数的信号量。
//它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做完自己的申请后归还，
//超过阈值后，线程申请许可信号将会被阻塞。Semaphore可以用来构建一些对象池，资源池之类的，
//比如数据库连接池，我们也可以创建计数为1的Semaphore，将其作为一种类似互斥锁的机制，
//这也叫二元信号量，表示两种互斥状态。计数为0的Semaphore是可以release的，
//然后就可以acquire（即一开始使线程阻塞从而完成其他执行。）。

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class PcpDemo4 {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Thread p1 = new Thread(new Producer(storage));
        Thread p2 = new Thread(new Producer(storage));
        Thread p3 = new Thread(new Producer(storage));
        Thread c1 = new Thread(new Consumer(storage));
        Thread c2 = new Thread(new Consumer(storage));
        Thread c3 = new Thread(new Consumer(storage));
        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }

    public static class Storage {
        private static final int MAX_SIZE = 10;
        private final LinkedList<Object> list = new LinkedList<>();
        private final Semaphore notEmpty = new Semaphore(0);
        private final Semaphore notFull = new Semaphore(MAX_SIZE);
        private final Semaphore mutex = new Semaphore(1);

        public void produce() {
            try {
                notFull.acquire();
                mutex.acquire();
                list.add(new Object());
                System.out
                    .println(String.format("生产者%s生产了一个产品。现在的库存是%d。", Thread.currentThread().getName(), list.size()));
            } catch(InterruptedException e) {
                e.printStackTrace();
            } finally {
                notEmpty.release();
                mutex.release();
            }
        }

        public void consume() {
            try {
                notEmpty.acquire();
                mutex.acquire();
                list.remove();
                System.out
                    .println(String.format("消费者%s消费了一个产品，现在的库存是%d", Thread.currentThread().getName(), list.size()));
            } catch(InterruptedException e) {
                e.printStackTrace();
            } finally {
                notFull.release();
                mutex.release();
            }
        }
    }

    public static class Consumer implements Runnable {
        private Storage storage;

        public Consumer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(3000);
                    storage.consume();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }

    public static class Producer implements Runnable {
        private Storage storage;

        public Producer(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(1000);
                    storage.produce();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    }
}

