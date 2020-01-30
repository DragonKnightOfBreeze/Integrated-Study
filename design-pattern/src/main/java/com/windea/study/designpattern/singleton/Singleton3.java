package com.windea.study.designpattern.singleton;

/**
 * 单例模式 懒汉式（线程不安全）
 */
public class Singleton3 {
	private Singleton3() {
	}

	private static Singleton3 instance;

	public static Singleton3 getInstance() {
		if(instance == null) {
			instance = new Singleton3();
		}
		return instance;
	}
}
