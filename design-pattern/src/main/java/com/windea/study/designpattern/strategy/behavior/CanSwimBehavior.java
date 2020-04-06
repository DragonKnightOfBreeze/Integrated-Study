package com.windea.study.designpattern.strategy.behavior;

public class CanSwimBehavior implements SwimBehavior {
    @Override
    public void swim() {
        System.out.println("这鸭子会游泳。");
    }
}
