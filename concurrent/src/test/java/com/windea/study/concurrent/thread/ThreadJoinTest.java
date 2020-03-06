package com.windea.study.concurrent.thread;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadJoinTest {
	private static Logger logger = LoggerFactory.getLogger("ThreadJoinTest");

	private static int r = 0;

	//为什么需要join
	@Test
	public void test1() {
		logger.debug("开始");
		var t1 = new Thread(() -> {
			logger.debug("开始");
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			logger.debug("结束");
			r = 10;
		});
		t1.start();
		logger.debug("结果为：{}", r);
		logger.debug("结束");
	}
}
