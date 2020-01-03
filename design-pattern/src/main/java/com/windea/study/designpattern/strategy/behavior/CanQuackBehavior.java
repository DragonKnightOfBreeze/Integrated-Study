package com.windea.study.designpattern.strategy.behavior;

public class CanQuackBehavior implements QuackBehavior {
	@Override
	public void quack() {
		System.out.println("这鸭子会嘎嘎叫。");
	}
}
