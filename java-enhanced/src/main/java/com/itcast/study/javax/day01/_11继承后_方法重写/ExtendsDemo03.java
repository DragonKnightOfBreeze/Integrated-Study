package com.itcast.study.javax.day01._11继承后_方法重写;

/**
 * 目标：静态方法和私有方法是否可以被重写（拓展语法）
 * <p>
 * 可以吗?  都不可以.
 */
public class ExtendsDemo03 {
}

class Mac extends Computer {
    // @Override
    public static void test() {
    }

    //@Override
    public void go() {
    }
}

class Computer {
    public static void test() {
    }

    private void go() {

    }
}
