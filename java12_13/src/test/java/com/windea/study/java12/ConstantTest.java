package com.windea.study.java12;


import org.junit.Test;

public class ConstantTest {
    @Test
    public void test1() {
        var str = "Hello world!";
        var strConst = str.describeConstable();
        System.out.println(strConst.orElse(null));
    }
}
