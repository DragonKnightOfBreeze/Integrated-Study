package com.windea.study.designpattern.singleton.impl1;

/**
 * 单例模式 饿汉式（静态常量）
 */
public class Singleton {
	private Singleton() {
	}

	private static final Singleton instance = new Singleton();

	public static Singleton getInstance() {
		return instance;
	}
}
