package com.windea.study.designpattern.singleton.impl7;

/**
 * 单例模式 静态内部类
 */
public class Singleton {
	private Singleton() {
	}

	private static class SingletonHolder {
		private static final Singleton instance = new Singleton();
	}

	public static Singleton getInstance() {
		return SingletonHolder.instance;
	}
}
