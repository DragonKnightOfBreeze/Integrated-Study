package com.windea.study.designpattern.strategy.behavior;

public class GoodFlyBehavior implements FlyBehavior {
	@Override
	public void fly() {
		System.out.println("这鸭子飞得很高。");
	}
}
