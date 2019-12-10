package com.windea.study.designpattern.singleton.impl2;

/**
 * 单例模式 饿汉式（静态代码块）
 */
public class Singleton {
	private Singleton() {
	}

	private static final Singleton instance;

	static {
		instance = new Singleton();
	}

	public static Singleton getInstance() {
		return instance;
	}
}
