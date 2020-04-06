package com.windea.study.designpattern.singleton;

/**
 * 单例模式 静态内部类
 */
public class Singleton7 {
    private Singleton7() {
    }

    public static Singleton7 getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final Singleton7 instance = new Singleton7();
    }
}
