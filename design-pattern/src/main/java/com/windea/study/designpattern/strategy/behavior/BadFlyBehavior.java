package com.windea.study.designpattern.strategy.behavior;

public class BadFlyBehavior implements FlyBehavior {
	@Override
	public void fly() {
		System.out.println("这鸭子飞得不高。");
	}
}
