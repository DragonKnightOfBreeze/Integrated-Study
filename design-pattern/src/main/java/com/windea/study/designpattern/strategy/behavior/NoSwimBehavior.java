package com.windea.study.designpattern.strategy.behavior;

public class NoSwimBehavior implements SwimBehavior {
	@Override
	public void swim() {
		System.out.println("这鸭子不会游泳。");
	}
}
