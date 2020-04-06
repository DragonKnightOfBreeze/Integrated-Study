package com.windea.study.interview.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AbaDemo {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        AtomicReference<Integer> v1 = new AtomicReference<>(100);

        new Thread(() -> {
            v1.compareAndSet(100, 101);
            v1.compareAndSet(101, 100);
        }, "t1").start();

        new Thread(() -> {
            sleep(1);
            var r1 = v1.compareAndSet(100, 2020);
            System.out.println(r1 + "\t" + v1.get());
        }, "t2").start();


        sleep(2);
        System.out.println("******");

        AtomicStampedReference<Integer> v2 = new AtomicStampedReference<>(100, 0);

        new Thread(() -> {
            var stamp = v2.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号：" + stamp);

            sleep(1);

            v2.compareAndSet(100, 101, v2.getStamp(), v2.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第2次版本号：" + stamp);

            v2.compareAndSet(101, 100, v2.getStamp(), v2.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "\t第3次版本号：" + stamp);
        }, "t3").start();

        new Thread(() -> {
            var stamp = v2.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号：" + stamp);

            sleep(3);

            var r = v2.compareAndSet(100, 2020, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "\t修改是否成功：" + r);
            System.out.println(Thread.currentThread().getName() + "\t当前实际最新值：" + v2.getReference());
        }, "t4").start();
    }

    private static void sleep(long s) {
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
