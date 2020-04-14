package com.windea.study.interview.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public static void main(String[] args) throws InterruptedException {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        }, "t1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {

            spinLockDemo.myLock();
            spinLockDemo.myLock();
        }, "t2").start();
    }

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " opened");
        while(!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + " closed");
    }
}
