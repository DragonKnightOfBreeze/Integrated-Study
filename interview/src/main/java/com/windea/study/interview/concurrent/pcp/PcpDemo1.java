package com.windea.study.interview.concurrent.pcp;

//使用Object类的wait()/notify()方法解决生产者/消费者问题

//当缓冲区已满时，生产者线程停止执行，放弃锁，使自己处于等状态，让其他线程执行；
//当缓冲区已空时，消费者线程停止执行，放弃锁，使自己处于等状态，让其他线程执行。
//
//当生产者向缓冲区放入一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态；
//当消费者从缓冲区取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。

import java.util.LinkedList;

public class PcpDemo1 {
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

        public void produce() {
            synchronized(list) {
                while(list.size() >= MAX_SIZE) {
                    System.out.println(String.format("生产者%s仓库已满。", Thread.currentThread().getName()));
                    try {
                        list.wait();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(new Object());
                System.out
                    .println(String.format("生产者%s生产了一个产品。现在的库存是%d。", Thread.currentThread().getName(), list.size()));
                list.notifyAll();
            }
        }

        public void consume() {
            synchronized(list) {
                while(list.size() <= 0) {
                    System.out.println(String.format("消费者%s仓库为空。", Thread.currentThread().getName()));
                    try {
                        list.wait();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.remove();
                System.out
                    .println(String.format("消费者%s消费了一个产品，现在的库存是%d", Thread.currentThread().getName(), list.size()));
                list.notifyAll();
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

