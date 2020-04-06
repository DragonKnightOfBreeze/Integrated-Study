package com.windea.study.java14;

import org.junit.Test;

public class PatternMatchTest {
    @Test
    public void test1() {
        Object obj = "hello world!";
        //不再需要进行强制转换
        //null值不会通过模式匹配
        if(obj instanceof String str) {
            System.out.println("obj是String类型");
            System.out.println(str.contains("hello"));
        } else {
            System.out.println("obj不是String类型");
        }
    }
}
