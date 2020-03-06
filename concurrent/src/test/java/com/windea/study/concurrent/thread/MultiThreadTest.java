package com.windea.study.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiThreadTest {
	private static Logger logger = LoggerFactory.getLogger("MultiThreadTest");

	public void test1() throws InterruptedException {
		var t1 = new Thread(() -> {
			while(true) {
				logger.debug("running");
			}
		}, "t1");
		var t2 = new Thread(() -> {
			while(true) {
				logger.debug("running");
			}
		}, "t2");
		t1.start();
		t2.start();
		t1.join(60_000);
		t2.join(60_000);
	}
}
