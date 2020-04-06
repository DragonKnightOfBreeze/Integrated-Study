package com.windea.study.interview.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        //如果取值时是5，则赋值为2048
        var r1 = atomicInteger.compareAndSet(5, 2048);
        System.out.println(r1 + "\tcurrent value: " + atomicInteger.get());

        var r2 = atomicInteger.compareAndSet(5, 1024);
        System.out.println(r2 + "\tcurrent value: " + atomicInteger.get());
    }
}
