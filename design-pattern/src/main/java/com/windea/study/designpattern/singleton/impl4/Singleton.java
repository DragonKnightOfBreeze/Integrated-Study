package com.windea.study.designpattern.singleton.impl4;

/**
 * 单例模式 懒汉式（线程安全，同步方法）
 */
public class Singleton {
	private Singleton() {
	}

	private static Singleton instance;

	public static synchronized Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}
