package com.windea.study.interview.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        var cache = new MyCache();
        for(int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(() -> {
                cache.put("key" + index, "value");
            }, String.valueOf(i)).start();
        }
        for(int i = 0; i < 5; i++) {
            final int index = i;
            new Thread(() -> {
                var result = cache.get("key" + index);
                System.out.println(result);
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private final Map<String, Object> map = new HashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在写入……");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成。");
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在读取……");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            var result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成。");
            return result;
        } finally {
            lock.readLock().unlock();
        }
    }
}
