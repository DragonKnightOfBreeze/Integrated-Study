package com.windea.study.designpattern.singleton;

/**
 * 单例模式 懒汉式（线程安全，同步方法）
 */
public class Singleton4 {
    private static Singleton4 instance;

    private Singleton4() {
    }

    public static synchronized Singleton4 getInstance() {
        if(instance == null) {
            instance = new Singleton4();
        }
        return instance;
    }
}
