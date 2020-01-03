package com.windea.study.designpattern.strategy.behavior;

public class NoFlyBehavior implements FlyBehavior {
	@Override
	public void fly() {
		System.out.println("这鸭子不会飞。");
	}
}
