package com.windea.study.designpattern.strategy;

import com.windea.study.designpattern.strategy.behavior.BadFlyBehavior;
import com.windea.study.designpattern.strategy.behavior.CanQuackBehavior;

public class PekingDuck extends Duck {
	public PekingDuck() {
		this.flyBehavior = new BadFlyBehavior();
		this.quackBehavior = new CanQuackBehavior();
	}

	@Override
	public void display() {
		System.out.println("这是一只北京鸭。");
	}
}
