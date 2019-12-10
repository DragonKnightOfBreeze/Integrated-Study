package com.windea.study.java8;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long> {
	private static final long serialVersionUID = 6212165578436873636L;

	private long start;
	private long end;

	//临界值
	private static final long THRESHOLD = 10000;

	public ForkJoinCalculate(long start, long end) {
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		long length = end - start;

		if(length <= THRESHOLD) {
			//如果到达临界值，再进行真正的计算逻辑
			long sum = 0;
			for(long i = start; i < end; i++) {
				sum += i;
			}
			return sum;
		} else {
			//否则拆分成两个相同区间的任务
			long middle = (start + end) / 2;
			var left = new ForkJoinCalculate(start, middle);
			//加入线程队列
			left.fork();
			var right = new ForkJoinCalculate(middle + 1, end);
			right.fork();
			//得到整合后的结果
			return left.join() + right.join();
		}
	}
}
