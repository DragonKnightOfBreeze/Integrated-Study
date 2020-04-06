package com.windea.study.designpattern.singleton;

/**
 * 单例模式 饿汉式（静态常量）
 */
public class Singleton1 {
    private static final Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }
}
