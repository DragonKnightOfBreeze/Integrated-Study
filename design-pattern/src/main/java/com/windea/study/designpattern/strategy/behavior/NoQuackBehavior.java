package com.windea.study.designpattern.strategy.behavior;

public class NoQuackBehavior implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("这鸭子不会叫。");
    }
}
