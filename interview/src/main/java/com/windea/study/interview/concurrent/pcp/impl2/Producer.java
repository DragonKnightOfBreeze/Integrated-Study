package com.windea.study.interview.concurrent.pcp.impl2;

/** 生产者。 */
public class Producer implements Runnable {
	private Storage storage;

	public Producer(Storage storage) {
		this.storage = storage;
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				storage.produce();
			} catch(InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
