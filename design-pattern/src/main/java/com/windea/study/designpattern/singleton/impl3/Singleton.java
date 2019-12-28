package com.windea.study.designpattern.singleton.impl3;

/**
 * 单例模式 懒汉式（线程不安全）
 */
public class Singleton {
	private Singleton() {
	}

	private static Singleton instance;

	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}