package com.windea.study.interview.concurrent.pcp;

//使用Lock类的await()/signal()方法解决生产者/消费者问题

//在JDK5中，用ReentrantLock和Condition可以实现等待/通知模型，具有更大的灵活性。
//通过在Lock对象上调用newCondition()方法，将条件变量和一个锁对象进行绑定，进而控制并发程序访问竞争资源的安全。

import java.util.LinkedList;
import java.util.concurrent.locks.*;

public class PcpDemo2 {
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
        private final Lock lock = new ReentrantLock();
        private final Condition empty = lock.newCondition();
        private final Condition full = lock.newCondition();

        public void produce() {
            lock.lock();
            while(list.size() >= MAX_SIZE) {
                System.out.println(String.format("生产者%s仓库已满。", Thread.currentThread().getName()));
                try {
                    full.await();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(new Object());
            System.out.println(String.format("生产者%s生产了一个产品。现在的库存是%d。", Thread.currentThread().getName(), list.size()));
            empty.signalAll();
            lock.unlock();
        }

        public void consume() {
            lock.lock();
            while(list.size() <= 0) {
                System.out.println(String.format("消费者%s仓库为空。", Thread.currentThread().getName()));
                try {
                    empty.await();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove();
            System.out.println(String.format("消费者%s消费了一个产品，现在的库存是%d", Thread.currentThread().getName(), list.size()));
            full.signalAll();
            lock.unlock();
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

