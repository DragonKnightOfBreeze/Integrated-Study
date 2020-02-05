package com.windea.study.interview.concurrent.pcp.impl2;

/** 消费者。 */
public class Consumer implements Runnable {
	private Storage storage;

	public Consumer(Storage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
				storage.consume();
			} catch(InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
