package com.windea.study.designpattern.strategy;

import com.windea.study.designpattern.strategy.behavior.*;

public abstract class Duck {
	protected FlyBehavior flyBehavior = new NoFlyBehavior();
	protected QuackBehavior quackBehavior = new NoQuackBehavior();
	protected SwimBehavior swimBehavior = new NoSwimBehavior();

	public abstract void display();

	public void quack() {
		flyBehavior.fly();
	}

	public void swim() {
		swimBehavior.swim();
	}

	public void fly() {
		flyBehavior.fly();
	}
}
