package com.windea.study.java8;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class ConcurrentTest {
    //数值越大时，效果更明显
    @Test
    public void normalTest() {
        var startTime = Instant.now();

        long sum = 0;
        for(long i = 0; i < Long.MAX_VALUE; i++) {
            sum += i;
        }
        System.out.println(sum);

        var endTime = Instant.now();
        System.out.println("耗费时间：" + Duration.between(startTime, endTime).toMillis());
    }

    @Test
    public void forkJoinTest() {
        var startTime = Instant.now();

        var pool = new ForkJoinPool();
        var task = new ForkJoinCalculate(0, Long.MAX_VALUE);
        var sum = pool.invoke(task);
        System.out.println(sum);

        var endTime = Instant.now();
        System.out.println("耗费时间：" + Duration.between(startTime, endTime).toMillis());
    }

    @Test
    public void test3() {
        //低层就是fork-join
        var startTime = Instant.now();

        var sum = LongStream.range(0, Long.MAX_VALUE).parallel().reduce(0, Long::sum);
        System.out.println(sum);

        var endTime = Instant.now();
        System.out.println("耗费时间：" + Duration.between(startTime, endTime).toMillis());
    }
}
