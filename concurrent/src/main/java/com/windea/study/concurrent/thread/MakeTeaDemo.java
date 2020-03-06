package com.windea.study.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakeTeaDemo {
	private static Logger logger = LoggerFactory.getLogger("MakeTeaDemo");

	public static void main(String[] args) throws InterruptedException {
		execute();
	}


	private static void execute() throws InterruptedException {
		var t1 = new Thread(() -> {
			logger.debug("洗水壶……");
			sleep(1);
			logger.debug("烧开水……");
			sleep(5);
		}, "t1");
		var t2 = new Thread(() -> {
			logger.debug("洗水壶……");
			sleep(1);
			logger.debug("洗茶杯……");
			sleep(2);
			logger.debug("拿茶叶……");
			sleep(1);
		}, "t2");
		var t3 = new Thread(() -> {
			logger.debug("泡茶……");
		}, "t3");

		t1.start();
		t2.start();
		t1.join();
		t2.join();
		t3.start();
		t3.join();
		logger.debug("完成。");

	}

	private static void sleep(long s) {
		try {
			Thread.sleep(s * 1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
