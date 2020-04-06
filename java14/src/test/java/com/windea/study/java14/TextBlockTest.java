package com.windea.study.java14;

import org.junit.Test;

public class TextBlockTest {
    //多行字符串，去除每行的最小缩进
    @Test
    public void test1() {
        var text = """
                   Hello,
                   World!
                   """;
        System.out.println(text);
    }

    //折行字符串，去除换行符
    @Test
    public void test2() {
        var text = """
                   Hello,\
                   World!
                   """;
        System.out.println(text);
    }

    @Test
    public void test3() {
        var text = "Hello,\nWorld!";
        System.out.println(text);
    }
}
