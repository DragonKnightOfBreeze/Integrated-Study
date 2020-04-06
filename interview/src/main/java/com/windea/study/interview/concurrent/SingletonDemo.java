package com.windea.study.interview.concurrent;

/**
 * 单例模式（双重检查）
 */
public class SingletonDemo {
    private static volatile SingletonDemo instance;

    public static SingletonDemo getInstance() {
        if(instance == null) {
            synchronized(SingletonDemo.class) {
                if(instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
