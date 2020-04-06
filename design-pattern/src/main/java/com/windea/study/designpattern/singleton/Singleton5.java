package com.windea.study.designpattern.singleton;

/**
 * 单例模式 懒汉式（线程安全，同步代码块）
 */
public class Singleton5 {
    private static Singleton5 instance;

    private Singleton5() {
    }

    public static Singleton5 getInstance() {
        if(instance == null) {
            synchronized(Singleton5.class) {
                instance = new Singleton5();
            }
        }
        return instance;
    }
}
