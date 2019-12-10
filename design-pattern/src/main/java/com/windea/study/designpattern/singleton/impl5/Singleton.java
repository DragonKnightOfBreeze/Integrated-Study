package com.windea.study.designpattern.singleton.impl5;

/**
 * 单例模式 懒汉式（线程安全，同步代码块）
 */
public class Singleton {
	private Singleton() {
	}

	private static Singleton instance;

	public static Singleton getInstance() {
		if(instance == null) {
			synchronized(Singleton.class) {
				instance = new Singleton();
			}
		}
		return instance;
	}
}
