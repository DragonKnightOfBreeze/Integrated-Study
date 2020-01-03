package com.windea.study.designpattern.strategy;

import com.windea.study.designpattern.strategy.behavior.*;

public class WildDuck extends Duck {
	public WildDuck() {
		this.flyBehavior = new GoodFlyBehavior();
		this.quackBehavior = new CanQuackBehavior();
		this.swimBehavior = new CanSwimBehavior();
	}

	@Override
	public void display() {
		System.out.println("这是一只野鸭。");
	}
}
